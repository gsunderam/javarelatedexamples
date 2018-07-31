package com.sg.dp.tree;

import static com.sg.dp.log.Logger.printTab;
import static com.sg.dp.log.Logger.stdout;
import static com.sg.dp.tree.BinarySearchTree.insertNode;
import static com.sg.dp.tree.BinarySearchTree.printTree;

/**
 * Created by chandrashekar on 12/30/2016.
 * Convert a binary search tree to a singly linked list. Left references ae nullified
 * and assigned to the right reference. Method #2. slightly different from BSTtoList class
 */
public class BSTToListRight {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        insertNodes(root);
        printTree(root);

        convertBstToList(root);
        printLinkedList(root);
    }

     private static void convertBstToList(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;

        convertBstToList(left);
        convertBstToList(right);
        stdout("processing root: " + root + " left: " + left + " right: " + right);

        if (left != null) {
            if (right != null) { //root has a right node
                while(right.right != null) right = right.right;
                right.right = left;
            } else root.right = left; //root does NOT have a right node
        }
    }

    private static void insertNodes(TreeNode root) {
        insertNode(root, new TreeNode(25));
        insertNode(root, new TreeNode(10));
        insertNode(root, new TreeNode(21));
        insertNode(root, new TreeNode(26));
        insertNode(root, new TreeNode(11));
        insertNode(root, new TreeNode(7));
        insertNode(root, new TreeNode(6));
        insertNode(root, new TreeNode(5));
        insertNode(root, new TreeNode(27));
        insertNode(root, new TreeNode(24));
        insertNode(root, new TreeNode(9));
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
