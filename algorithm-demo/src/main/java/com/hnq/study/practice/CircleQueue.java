package com.hnq.study.practice;

import com.hnq.toolkit.util.StrUtils;

import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/09/27
 */
public class CircleQueue {

    public static void main(String[] args) {
        myQueueTest();
    }

    private static void myQueueTest() {

        // 队列有效数据个数为3
        MyQueue<Integer> q = new MyQueue<>(4);

        boolean loop = true;
        Scanner sc = new Scanner(System.in);
        while (loop) {
            System.out.println("a(add) - g(get) - p(peek) - s(show) - e(exit)");
            String in = sc.next();
            switch (in) {
                case "a":
                    System.out.println("Input an integer:");
                    int x = sc.nextInt();
                    try {
                        q.add(x);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "g":
                    try {
                        System.out.println(q.get());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "p":
                    try {
                        System.out.println(q.peek());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "s":
                    try {
                        q.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    sc.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("Exit.");
    }

}

/**
 * 满：(tail + 1) % maxSize = front
 * 空：tail == front
 * 有效数据个数：(tail + maxSize - front) % maxSize
 *
 * @author henengqiang
 * @date 2019-09-30
 */
class MyQueue<T> {

    private int maxSize;

    /**
     * 指向队列第一个元素
     */
    private int front;

    /**
     * 指向队列最后一个元素的后一个位置
     */
    private int tail;

    private T[] data;

    @SuppressWarnings("unchecked")
    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
        data = (T[]) new Object[maxSize];
    }

    public boolean isFull() {
        return (tail + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return tail == front;
    }

    public void add(T t) {
        if (isFull()) {
            throw new RuntimeException("队列满，无法添加");
        }
        data[tail] = t;
        tail = (tail + 1) % maxSize;
    }

    public T get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法获取");
        }
        T t = data[front];
        data[front] = null;
        front = (front + 1) % maxSize;
        return t;
    }

    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法获取");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.print(StrUtils.format("arr[{}]={} ", i % maxSize,  data[i % maxSize]));
        }
        System.out.println();
    }

    private int size() {
        return (tail + maxSize - front) % maxSize;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法获取");
        }
        return data[front];
    }

}
