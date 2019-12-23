package com.hnq.study.lintcode;

/**
 * @author henengqiang
 * @date 2019/09/05
 */
public class MaxNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode secondLeft = new TreeNode(-5);
        TreeNode secondRight = new TreeNode(3);
        TreeNode thirdLeftLeft = new TreeNode(1);
        TreeNode thirdLeftRight = new TreeNode(2);
        TreeNode thirdRightLeft = new TreeNode(-4);
        TreeNode thirdRightRight = new TreeNode(-5);
        root.left = secondLeft;
        root.right = secondRight;
        secondLeft.left = thirdLeftLeft;
        secondLeft.right = thirdLeftRight;
        secondRight.left = thirdRightLeft;
        secondRight.right = thirdRightRight;

        TreeNode r = maxNode(root);
        System.out.println(r.val);
    }

    private static TreeNode maxNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        return getMaxNode(root);
    }

    private static TreeNode getMaxNode(TreeNode root) {
        TreeNode maxNode = root;
        if (root.left != null) {
            maxNode = max(maxNode, getMaxNode(root.left));
        }
        if (root.right != null) {
            maxNode = max(maxNode, getMaxNode(root.right));
        }
        return maxNode;
    }

    private static TreeNode max(TreeNode a, TreeNode b) {
        return a.val > b.val ? a : b;
    }

}
