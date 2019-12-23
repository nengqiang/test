package com.hnq.study.localcacheimpl1;

import com.hnq.toolkit.util.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 假设在LocalCache中仅仅使用一个LRU Map。将产生性能问题：1. 单个LinkedHashMap中元素数量太多 2. 高并发下读写锁限制。
 * 所以能够在LocalCache中使用多个LRU Map，并使用key 来 hash到某个LRU Map上，以此来提高在单个LinkedHashMap中检索的速度以及提高总体并发度。
 *
 * 这里hash选用了Wang/Jenkins hash算法。实现Hash的方式參考了ConcurrentHashMap的实现。
 *
 * @author henengqiang
 * @date 2019/06/25
 */
public class MyLocalCache {

    private int size;

    /**
     * 在数字没有溢出的前提下，对于正数和负数，左移一位都相当于乘以2的1次方，左移n位就相当于乘以2的n次方
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 本地缓存支持的最大分区数
     */
    static final int MAX_SEGMENTS = 1 << 16;

    /**
     * 本地缓存存储的LRUMap数组
     */
    LRUMap<CacheObject>[] segments;

    /**
     * Mask value for indexing into segments. The upper bits of a key's hash code are used to choose the segment.
     */
    int segmentMask;

    /**
     * Shift value for indexing within segments.
     */
    int segmentShift;

    /**
     * 计数器重置阀值
     */
    private static final int MAX_LOOKUP = 100000000;

    /**
     * 用于计数器的锁，防止多次重置计数器
     */
    private final Lock lock = new ReentrantLock();

    /**
     * Number of requests made to lookup a cache entry.
     */
    private AtomicLong lookup = new AtomicLong(0);

    /**
     * Number of successful requests for cache entries.
     */
    private AtomicLong found = new AtomicLong(0);

    public MyLocalCache(int size) {
        this.size = size;
    }

    public CacheObject get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        // 添加计数器
        lookup.incrementAndGet();
        // 假设必要的重置计数器
        if (lookup.get() > MAX_LOOKUP) {
            if (lock.tryLock()) {
                try {
                    lookup.set(0);
                    found.set(0);
                } finally {
                    lock.unlock();
                }
            }
        }
        int hash = hash(key.hashCode());
        CacheObject ret = segmentFor(hash).getEntry(key);
        if (ret != null) {
            found.incrementAndGet();
        }
        return ret;
    }

    public void remove(String key) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        int hash = hash(key.hashCode());
        segmentFor(hash).remove(key);
    }

    public void put(String key, CacheObject val) {
        if (StringUtils.isBlank(key) || val == null) {
            return;
        }
        int hash = hash(key.hashCode());
        segmentFor(hash).addEntry(key, val);
    }

    public synchronized void clearCache() {
        for (LRUMap<CacheObject> segment : segments) {
            segment.clear();
        }
    }

    public synchronized void reload() {
        clearCache();
        init();
    }

    public synchronized void dumpLocalCache() {
        for (int i = 0; i < segments.length; i++) {
            String tempDir = System.getProperty("java.io.tmpdir");
            String fileName = tempDir + File.separator + "localCache-dump-file" + i + ".cache";
            File file = new File(fileName);
            FileUtils.writeObjectToFile(file, segments[i]);
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized void restoreLocalCache() {
        for (int i = 0; i < segments.length; i++) {
            String tempDir = System.getProperty("java.io.tmpdir");
            String fileName = tempDir + File.separator + "localCache-dump-file" + i + ".cache";
            File file = new File(fileName);
            LRUMap<CacheObject> lruMap = (LRUMap<CacheObject>) FileUtils.readFileToObject(file);
            if (lruMap != null) {
                Set<HashMap.Entry<String, SoftReference<CacheObject>>> set = lruMap.entrySet();
                for (HashMap.Entry<String, SoftReference<CacheObject>> entry : set) {
                    if (entry.getValue() != null && entry.getValue().get() != null) {
                        segments[i].addEntry(entry.getKey(), entry.getValue().get());
                    }
                }
            }
        }
    }

    /**
     * 本地缓存命中次数，在计数器RESET的时刻可能会出现0的命中率
     */
    public int getHitRate() {
        long query = lookup.get();
        return query == 0 ? 0 : (int) ((found.get() * 100) / query);
    }

    /**
     * 本地缓存访问次数，在计数器RESET时可能会出现0的命中率
     */
    public long getCount() {
        return lookup.get();
    }

    public int size() {
        final LRUMap<CacheObject>[] segments = this.segments;
        long sum = 0;
        for (LRUMap<CacheObject> segment : segments) {
            sum += segment.size();
        }
        if (sum > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) sum;
        }
    }

    /**
     * 无符号右移运算符和右移运算符的主要区别在于负数的计算，因为无符号右移是高位补0，移多少位补多少个0。
     * 15的二进制位是0000 1111 ， 右移2位0000 0011，结果为3
     *
     * Returns the segment that should be used for key with given hash
     *
     * @param hash  the hash code for the key
     * @return the segment
     */
    final LRUMap<CacheObject> segmentFor(int hash) {
        return segments[(hash >>> segmentShift) & segmentMask];
    }

    /* ---------------- Small Utilities -------------- */

    private static int hash(int h) {
        // Spread bits to regularize both segment and index locations,
        // using variant of single-word Wang/Jenkins hash.
        h += (h << 15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h << 3);
        h ^= (h >>> 6);
        h += (h << 2) + (h << 14);
        return h ^ (h >>> 16);
    }

    @SuppressWarnings("unchecked")
    public void init() {
        int concurrencyLevel = 16;
        int capacity = size;
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        // Find power-of-two sizes best matching arguments
        int sshift = 0;
        int ssize = 1;
        while (ssize < concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }
        segmentShift = 32 - sshift;
        segmentMask = ssize - 1;
        this.segments = new LRUMap[ssize];
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }
        int c = capacity / ssize;
        if (c * ssize < capacity) {
            ++c;
        }
        int cap = 1;
        while (cap < c) {
            cap <<= 1;
        }
        cap >>= 1;
        for (int i = 0; i < this.segments.length; ++i) {
            this.segments[i] = new LRUMap<>(cap);
        }
    }

    /**
     * // TODO: 2019-06-25 henengqiang 对象待修改
     */
    static class CacheObject {

        private final Object object;

        private long createTime = System.currentTimeMillis();

        CacheObject(Object obj) {
            this.object = obj;
        }

        public boolean isExpired(long timeout) {
            return System.currentTimeMillis() - this.createTime >= timeout;
        }

        public Object getObject() {
            return this.object;
        }

        public void expireObject() {
            this.createTime = 0L;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
