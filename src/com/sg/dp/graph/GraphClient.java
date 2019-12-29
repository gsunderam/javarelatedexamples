package com.sg.dp.graph;

import java.util.List;
import java.util.Set;

import static com.sg.dp.log.Logger.print;
import static com.sg.dp.log.Logger.stdout;

public class GraphClient {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("D", "F");
        graph.addEdge("E", "F");
        graph.addEdge("E", "C");

        List<Vertex> vertices = graph.getVertices("A");
        stdout("Vertices for A are -> " + vertices);

        graph.printGraph();
        print("\n");
        graph.traverseDFS("A");
        graph.traverseBFS("A");
        graph.printPath("D", "E", new StringBuilder(""));
    }
}
