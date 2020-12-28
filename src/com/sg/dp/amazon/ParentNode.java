package com.sg.dp.amazon;

import java.util.ArrayList;
import java.util.List;

public class ParentNode {
    public int data;
    public List<ParentNode> children;
    public boolean visited = false;

    public ParentNode(int data) {
        this.data = data;
    }

    public ParentNode(int data, List<ParentNode> children) {
        this.data = data;
        this.children = children;
    }

    @Override
    public String toString() {
        return "Node{" + data + '}';
    }
}
