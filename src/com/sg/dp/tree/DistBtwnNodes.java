package com.sg.dp.tree;

import com.sg.dp.log.Logger;

public class DistBtwnNodes {
    public int calcDistance(TreeNode root, TreeNode p, TreeNode q) {
        /** Check whether either node covers the other */
        int result = distanceHelper(p, q, 0);
        if (result == 0) result = distanceHelper(q, p, 0);

        /** Else nodes are in different sub trees */
        if (result == 0) {
            TreeNode parent = locateParent(root, p, q);
            TreeNode first = parent.left == p || parent.right == p ? p : q;
            TreeNode second = first == p ? q : p;
            /**  Move up the tree starting with p's parent until q is found */
            while(parent != null) {
                TreeNode sibling = getSibling(parent, first);
                int dist = distanceHelper(sibling, second, 0);
                result += dist;
                if (dist != 0) { //final answer is + 2 to account for 2 nodes away
                    return result + 2;
                }

                result++; // increase everytime we move up a parent
                first = parent;
                parent = locateParent(root, parent, null);
            }
        }

        return result;
    }

    public TreeNode locateParent(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (isChild(root, p) || isChild(root, q)) return root;

        TreeNode parent = locateParent(root.left, p, q);
        if (parent != null) return parent;
        parent = locateParent(root.right, p, q);
        return parent;
    }

    public TreeNode getSibling(TreeNode n, TreeNode target) {
        return n.left != null && n.left == target ? n.right : n.left;
    }

    public int distanceHelper(TreeNode p, TreeNode q, int count) {
        if (p == null)  return 0;
        if (p.value == q.value) return count;

        int result = distanceHelper(p.left, q, count + 1);
        if (result != 0) return result;
        return distanceHelper(p.right, q, count + 1);
    }

    private boolean isChild(TreeNode parent, TreeNode child) {
        if (child == null) return false;

        return ((parent.left != null && parent.left == child) ||
                (parent.right != null && parent.right == child));
    }

    public static void main(String[] args) {
        DistBtwnNodes distBtwnNodes = new DistBtwnNodes();
        TreeNode root = new TreeNode(51);
        BinarySearchTree.insertNodes(root);
        int result = distBtwnNodes.calcDistance(root, BinarySearchTree.CHILD_19, BinarySearchTree.CHILD_61);
        Logger.stdout("Result: " + result);
    }
}
