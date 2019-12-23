package com.hnq.study.practice;

/**
 * @author henengqiang
 * @date 2019/12/06
 */
public class RedBlackTree1 {

    private final int R = 0;
    private final int B = 1;

    private Node root = null;

    public void insert(Node root, int data) {
        if (data > root.data) {
            if (root.right == null) {
                root.right = new Node(data);
            } else {
                insert(root.right, data);
            }
        } else {
            if (root.left == null) {
                root.left = new Node(data);
            } else {
                insert(root.left, data);
            }
        }
    }

    public void rotateLeft(Node node) {
        if (node.parent == null) {
            Node E = root;
            Node S = E.right;

            S.parent = null;
            E.right = S.left;
            E.parent = S;
        }
    }

    public void rotateRight(Node node) {
        if (node.parent == null) {

        }
    }

    private class Node {
        int data;
        /**
         * 插入默认为红色
         */
        int color = R;
        Node left;
        Node right;
        Node parent;

        Node(int data) {
            this.data = data;
        }
    }
}
