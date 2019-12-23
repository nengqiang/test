package com.hnq.study.pta;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/11/29
 */
public class Q1025 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        logic();
    }

    private static void logic() {
        String in = sc.nextLine();
        String[] ins = in.split("\\s");
        int len = Integer.parseInt(ins[1]);
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            String nodeIn = sc.nextLine();
            String[] nodeData = nodeIn.split("\\s");
            nodes[i] = new Node(nodeData[0], Integer.parseInt(nodeData[1]), nodeData[2]);
        }

        Map<String, Node> addrToNode = new HashMap<>(len * 4 / 3 + 1);
        for (Node node : nodes) {
            addrToNode.put(node.getAddress(), node);
        }

        String[] addr = new String[len];
        nodes[0] = addrToNode.get(ins[0]);
        addr[0] = nodes[0].getAddress();
        for (int i = 1; i < len; i++) {
            nodes[i] = addrToNode.get(nodes[i - 1].getNextAddress());
            addr[i] = nodes[i].getAddress();
        }

        for (Node node : nodes) {
            System.out.println(node);
        }

        reverseAddr(addr, Integer.parseInt(ins[2]));

        for (String a : addr) {
            Node n = addrToNode.get(a);
            System.out.println(a + " " + n.getData() + " " + n.getNextAddress());
        }
    }

    private static void reverseAddr(String[] arr, int k) {
        if (arr == null || k <= 0 || k > arr.length) {
            return;
        }
        int trade = arr.length / k;
        for (int i = 0; i < trade; i++) {
            reverse(arr, i * k, (i + 1) * k - 1);
        }
    }

    private static void reverse(String[] arr, int left, int right) {
        if (arr == null || arr.length == 1) {
            return;
        }
        while (left < right) {
            String temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    private static class Node {
        private String address;
        private int data;
        private String nextAddress;

        public Node(String address, int data, String nextAddress) {
            this.address = address;
            this.data = data;
            this.nextAddress = nextAddress;
        }

        public String getAddress() {
            return address;
        }

        public int getData() {
            return data;
        }

        public String getNextAddress() {
            return nextAddress;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "address='" + address + '\'' +
                    ", data=" + data +
                    ", nextAddress='" + nextAddress + '\'' +
                    '}';
        }
    }

}
