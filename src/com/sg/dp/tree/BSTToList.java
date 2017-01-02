package com.sg.dp.tree;

import static com.sg.dp.log.Logger.printTab;
import static com.sg.dp.log.Logger.stdout;
import static com.sg.dp.tree.BinarySearchTree.insertNode;

/**
 * Created by chandrashekar on 12/30/2016.
 * Convert a binary search tree to a singly linked list. Left references ae nullified
 * and assigned to the right reference
 */
public class BSTToList {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(51);
        insertNodes(root);
//        BinarySearchTree.printTree(root);

        convertTreeToList(root);
        printLinkedList(root);
    }

    /**
     * Transforms the tree into a sinly linked list recursively
     * @param root Root or top of the tree
     */
    private static void convertTreeToList(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;
        stdout("processing " + root + " left: " + left + " right " + right);
        root.left = null;
        convertTreeToList(left);
        convertTreeToList(right);

        if (left != null) { //Added null checks to eliminate unnecessary processing. It does make code a tad bit unreadeable -:)
            stdout("Assigning node " + root + ".right to " + left);
            root.right = left;
            if (right != null) {
                /** This loop gets evaluated ONLY once in the last iteration when the immediate left is NOT a terminal node */
                while(left.right != null) left = left.right;
                stdout("Assigning " + left + ".right to " + right);
                left.right = right;
            }
        }
    }

    private static void insertNodes(TreeNode root) {
        insertNode(root, new TreeNode(20));
        insertNode(root, new TreeNode(61));
        insertNode(root, new TreeNode(65));
        insertNode(root, new TreeNode(52));
        insertNode(root, new TreeNode(19));
        insertNode(root, new TreeNode(30));
        insertNode(root, new TreeNode(66));
        insertNode(root, new TreeNode(63));
        insertNode(root, new TreeNode(25));
        insertNode(root, new TreeNode(35));
        stdout("---------------------------------");
    }

    public static void printLinkedList(TreeNode root) {
        stdout("----------Output is--------------------");
        while (root != null) {
            printTab(root);
            root = root.right;
        }

        stdout("\n----------END--------------------");
    }
}
