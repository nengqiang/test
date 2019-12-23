package com.hnq.study.localcacheimpl1;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现LocalCache功能目标
 *      实现一个全局范围的LocalCache，各个业务点使用自己的Namespace对LocalCache进行逻辑分区。
 *      所以在LocalCache中进行读写采用的key为(namespace+(分隔符)+数据key)。如存在下面的一对keyValue :  NameToAge,Troy -> 23 。
 *      要求LocalCache线程安全，且LocalCache中总keyValue数量可控，提供清空，调整大小，dump到本地文件等一系列操作。
 *
 * LRU Map的实现
 *
 * @author henengqiang
 * @date 2019/06/25
 */
public class LRUMap<T> extends LinkedHashMap<String, SoftReference<T>> implements Externalizable {

    /**
     * The maximum size of the cache.
     */
    private int maxCacheSize;

    /**
     * Lock for map.
     */
    private final Lock lock = new ReentrantLock();

    /**
     * 默认构造函数，LRUMap的大小为Integer.MAX_VALUE
     */
    public LRUMap() {
        super();
        maxCacheSize = Integer.MAX_VALUE;
    }

    /**
     * Construct a new, empty cache with specified maximum size.
     */
    public LRUMap(int size) {
        super(size + 1,1f, true);
        maxCacheSize = size;
    }

    /**
     * 让LinkedHashMap支持LRU。假设Map的大小超过了预订值，返回true。LinkedHashMap自身实现返回false，即永不删除元素。
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<String, SoftReference<T>> eldest) {
        return size() > maxCacheSize;
    }

    @Override
    public SoftReference<T> remove(Object key) {
        try {
            lock.lock();
            return super.remove(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        Iterator<Map.Entry<String, SoftReference<T>>> it = size() > 0 ? entrySet().iterator() : null;
        // write out size
        out.writeInt(size());
        // write out keys and values
        if (it != null) {
            while (it.hasNext()) {
                Map.Entry<String, SoftReference<T>> entry = it.next();
                if (entry != null && entry.getValue() != null && entry.getValue().get() != null) {
                    out.writeObject(entry.getKey());
                    out.writeObject(entry.getValue().get());
                }
            }
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // read in size
        int size = in.readInt();
        // read the keys and values, and put the mappings in the map
        for (int i = 0; i < size; i++) {
            String key = (String) in.readObject();
            @SuppressWarnings("unchecked")
            T value = (T) in.readObject();
            addEntry(key, value);
        }
    }

    public T addEntry(String key, T entry) {
        try {
            SoftReference<T> srEntry = new SoftReference<>(entry);
            lock.lock();
            put(key, srEntry);
        } finally {
            lock.unlock();
        }
        return entry;
    }

    public T getEntry(String key) {
        SoftReference<T> srEntry;
        try {
            lock.lock();
            if ((srEntry = get(key)) == null) {
                return null;
            }
            // if soft reference is null then the entry has been garbage collected and so the key should be also removed.
            if (srEntry.get() == null) {
                remove(key);
                return null;
            }
        } finally {
            lock.unlock();
        }
        return srEntry.get();
    }

}
