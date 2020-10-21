package com.sg.dp.tree;

import static com.sg.dp.log.Logger.stdout;

/**
 * Created by chandrashekar on 10/19/2016.
 *
 * This is an implementation of the Binary Search Tree. Left < Node <= Right
 */
public class BinarySearchTree {

    public static final TreeNode CHILD_20 = new TreeNode(20);
    public static final TreeNode CHILD_40 = new TreeNode(40);
    public static final TreeNode CHILD_19 = new TreeNode(19);
    public static final TreeNode CHILD_61 = new TreeNode(61);
    public static final TreeNode CHILD_60 = new TreeNode(60);
    public static final TreeNode CHILD_65 = new TreeNode(65);
    public static final TreeNode CHILD_30 = new TreeNode(30);

    public static void main(String[] args) {
        TreeNode root = new TreeNode(51);
        insertNodes(root);
        printTree(root);

        searchTree(root, 52); //RightSide
        stdout("---------------------------------");
        searchTree(root, 40); //LeftSide
        stdout("---------------------------------");
        searchTree(root, 49); //Not found
        stdout("---------------------------------");
    }

     static void insertNodes(TreeNode root) {
        insertNode(root, CHILD_20);
        insertNode(root, BinarySearchTree.CHILD_61);
        insertNode(root, CHILD_65);
        insertNode(root, new TreeNode(52));
        insertNode(root, CHILD_19);
        insertNode(root, CHILD_30);
        insertNode(root, new TreeNode(25));
        insertNode(root, CHILD_40);
//        insertNode(root, new TreeNode(51));
        insertNode(root, CHILD_60);
        stdout("---------------------------------");
    }

    /**
     * Insert nodes such that it is L < N <R, recursively till the spot is found
     * @param root
     * @param child
     */
    static void insertNode(TreeNode root, TreeNode child) {
        if (child == null) return;

        if (child.value >= root.value) {
            if (root.right == null) {
//                stdout("Inserting right node " + child.value);
                root.right = child;
                return;
            } else {
                insertNode(root.right, child);
            }
        } else if (child.value < root.value) {
            if (root.left == null) {
//                stdout("Inserting left node " + child.value);
                root.left = child;
                return;
            } else {
                insertNode(root.left, child);
            }
        }
    }

    /**
     * Prints the tree like so "Node: 45 Left: 34 Right: 44"
     * @param root the root or top node in the tree
     */
     static void printTree(TreeNode root) {
        if (root == null) return;

        if (root.left != null || root.right != null) stdout("Node Value: " + root.value);

        if (root.left != null) {
            stdout("Left Value: " + root.left.value);
        }

        if (root.right != null) {
            stdout("Right Value: " + root.right.value);
        }

        stdout("---------------------------------");
        printTree(root.left);
        printTree(root.right);
    }

    /**
     * Search the given value either in the L or R depending on the Value passed.
     * @param root Root note of the tree or the Top node
     * @param searchValue Needs to be > ZERO
     * @return void
     */
    private static void searchTree(final TreeNode root, final int searchValue) {
        if (root == null || searchValue <= 0) {
            stdout("The number " + searchValue + " was not found");
            return;
        }

        if (searchValue == root.value) {
            stdout("VALUE FOUND!!!!!! " + searchValue);
            stdout("Left: " + (root.left != null ? root.left.value : root.left));
            stdout("Right: " + (root.right != null ? root.right.value : root.right));
            return;
        }

        if (searchValue > root.value) searchTree(root.right, searchValue);
        else searchTree(root.left, searchValue);
    }
}