package com.hnq.study.practice;

import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/09/27
 */
public class MyQueueTest {

    public static void main(String[] args) {
        myQueueTest();
    }

    private static void myQueueTest() {

        MyQueue<Integer> q = new MyQueue<>(3);

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
                    System.out.println(q.toString());
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
