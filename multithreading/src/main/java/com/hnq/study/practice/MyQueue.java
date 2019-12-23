package com.hnq.study.practice;

import java.util.Arrays;

/**
 * // TODO: 2019-09-27 henengqiang 一次性队列 待修改
 *
 * @author henengqiang
 * @date 2019/09/27
 */
public class MyQueue<T> {

    private int size;

    private int front;

    private int tail;

    private T[] data;

    @SuppressWarnings("unchecked")
    public MyQueue(int size) {
        this.size = size;
        data = (T[]) new Object[size];
        front = tail = -1;
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "size=" + size +
                ", front=" + front +
                ", tail=" + tail +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public boolean isFull() {
        return (Math.abs((front < 0 ? 0 : front) - (tail < 0 ? 0 : tail)) == size - 1);
    }

    public boolean isEmpty() {
        return front == tail;
    }

    public void add(T t) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法添加");
        }
        tail++;
        if (tail > size - 1) {
            tail = 0;
        }
        data[tail] = t;
    }

    public T get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取");
        }
        if (front < 0) {
            front = 0;
        }
        T temp = data[front];
        data[front] = null;
        front++;
        if (front > size - 1) {
            front = 0;
        }
        return temp;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取");
        }
        return front < 0 ? data[0] : data[front];
    }

}


