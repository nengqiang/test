package com.hnq.study.lock;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/11/25
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache<Integer, Integer> myCache = new MyCache<>();
        IntStream.range(0, 5).forEach(i -> new Thread(() -> myCache.put(i, i), String.valueOf(i)).start());
        IntStream.range(0, 5).forEach(i -> new Thread(() -> myCache.get(i), String.valueOf(i)).start());
    }

    private static class MyCache<K, V> {

        private final Map<K, V> CACHE = Maps.newHashMap();

        private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        public void put(K k, V v) {
            rwLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "正在写入...");
                TimeUnit.MILLISECONDS.sleep(300);
                CACHE.put(k, v);
                System.out.println(Thread.currentThread().getName() + "写入完成");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rwLock.writeLock().unlock();
            }
        }

        public V get(K k) {
            rwLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "正在读取---");
                TimeUnit.MILLISECONDS.sleep(100);
                V v = CACHE.get(k);
                System.out.println(Thread.currentThread().getName() + "读取到" + v);
                return v;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rwLock.readLock().unlock();
            }
            return null;
        }

        public Map<K, V> getCACHE() {
            return CACHE;
        }

        public void clearCache() {
            CACHE.clear();
        }
    }
}
