package com.sg.dp.utils;

import com.sg.dp.amazon.ParentNode;
import com.sg.dp.log.Logger;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {
    /**
     * Create the tree from botom up.
     * @param root
     */
    public static void createNodes(ParentNode root) {
        //Create the bottom 3 terminal nodes. Attach to leftMiddle
        List<ParentNode> leftBottomList = new ArrayList<>();
        leftBottomList.add(new ParentNode(15));
        leftBottomList.add(new ParentNode(12));
        leftBottomList.add(new ParentNode(10));

        //Middle level and attach to root
        ParentNode leftMiddle = new ParentNode(25, leftBottomList);
        List<ParentNode> rootList = new ArrayList<>();
        rootList.add(leftMiddle);
        ParentNode rightMiddle = new ParentNode(22);
        rootList.add(rightMiddle);
        root.children = rootList;

        //attach right middle to bottom right
        List<ParentNode> rightBottomList = new ArrayList<>();
        rightBottomList.add(new ParentNode(19));
        rightBottomList.add(new ParentNode(23));
        rightMiddle.children = rightBottomList;
    }

    public static void printTree(ParentNode root) {
        List<ParentNode> children = root.children;
        if (children == null || children.isEmpty()) return;

        Logger.stdout("root: " + root.data);
        root.visited = true;
        for (ParentNode node: children) {
            printTree(node);
            if (!node.visited) {
                Logger.stdout("Child Data: " + node.data);
            }
        }
    }

    public static void main(String[] args) {
        ParentNode root = new ParentNode(14);
        createNodes(root);
        printTree(root);
    }
}
