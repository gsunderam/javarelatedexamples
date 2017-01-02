package com.sg.dp.tree;

/**
 * Generic class to represent a binary tree structure. Each node will have
 * a maximum of two child nodes viz. Left and Right
 * Created by chandrashekar on 10/19/2016.
 */
public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode() {}

    public TreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}