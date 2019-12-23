package com.hnq.study.practice;

/**
 * @author henengqiang
 * @date 2019/12/06
 * @see <a href="https://www.jianshu.com/p/642ca913fa87">Blog</a>
 */
public class RBTree<K extends Comparable<K>, V> {

    private Node root;

    public int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.left.N + x.right.N;
    }

    public void flipColor(Node h) {
        h.color = Color.RED.getRed();
        h.left.color = Color.BLACK.getRed();
        h.right.color = Color.BLACK.getRed();
    }

    public void put(K key, V val) {
        root = put(root, key, val);
        root.color = Color.BLACK.getRed();
    }

    @SuppressWarnings("unchecked")
    public Node put(Node h, K key, V val) {
        if (h == null) {
            return new Node<>(key, val, 1, Color.RED.getRed());
        }
        int cmp = key.compareTo((K) h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColor(h);
        }
        h.N = size(h.left) + size(h.right);
        return h;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = Color.BLACK.getRed();
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return null;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = Color.RED.getRed();
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == Color.RED.getRed();
    }

    private static class Node<K extends Comparable<K>, V> {
        K key;
        V val;
        Node left, right;
        /**
         * 子数中的节点总数
         */
        int N;
        boolean color;

        Node(K key, V val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    private enum Color {
        RED("红色", true), BLACK("黑色", false);

        private String name;
        private Boolean isRed;

        Color(String name, boolean isRed) {
            this.name = name;
            this.isRed = isRed;
        }

        public String getName() {
            return name;
        }

        public Boolean getRed() {
            return isRed;
        }
    }
}
