package com.sg.dp.amazon;

import com.sg.dp.log.Logger;
import com.sg.dp.utils.TreeUtils;

import java.util.List;

public class SubTreeAvg {
    int maxAvg = Integer.MIN_VALUE;
    ParentNode maxavgNode = null;

    public ParentNode findMaxSubtreeAvg(ParentNode root) {
        if (root == null) return null;
        if (root.children == null || root.children.isEmpty()) return root;

        subtreeHelper(root);
        return maxavgNode;
    }

    public int[] subtreeHelper(ParentNode root) {
        List<ParentNode> children = root.children;
        if (children == null || children.isEmpty()) return new int[] {root.data, 1};

        int sum = root.data;
        int numNodes = 1;
        for (ParentNode node: children) {
            int [] sumAndCount = subtreeHelper(node);
            sum += sumAndCount[0];
            numNodes += sumAndCount[1];
        }

        int avg = sum / numNodes;
        Logger.stdout("Avg is " + avg + ", numNodes: " + numNodes + ", sum is " + sum + " for root: " + root.data);
        if (avg > maxAvg) {
            maxAvg = avg;
            maxavgNode = root;
            Logger.stdout("Max Avg is " + avg);
        }

        return new int[] {sum, numNodes};
    }

    public static void main(String[] args) {
        SubTreeAvg subTreeAvg = new SubTreeAvg();
        ParentNode root = new ParentNode(14);
        TreeUtils.createNodes(root);
        subTreeAvg.findMaxSubtreeAvg(root);
        Logger.stdout("Max subtree node is " + subTreeAvg.maxavgNode);
    }
}
