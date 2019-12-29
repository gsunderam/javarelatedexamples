package com.sg.dp.graph;

import java.util.*;

import static com.sg.dp.log.Logger.*;

public class Graph {
    Map<Vertex, List<Vertex>> adjacentVertices = new LinkedHashMap<>();

    public void addVertex(String name) {
        adjacentVertices.putIfAbsent(Flyweight.getVertex(name), new ArrayList<>());
    }

    public void addEdge(String src, String dest) {
        Vertex source = Flyweight.getVertex(src);
        Vertex destination = Flyweight.getVertex(dest);
        adjacentVertices.get(source).add(destination);
        adjacentVertices.get(destination).add(source);
    }

    public List<Vertex> getVertices(String name) {
        return adjacentVertices.get(Flyweight.getVertex(name));
    }

    public Vertex getVertex(String key) {
        Set<Vertex> vertices = adjacentVertices.keySet();
        return vertices.parallelStream().filter(v -> v.name.equalsIgnoreCase(key)).findFirst().get();
    }

    public void printGraph() {
        Set<Vertex> vertices = this.adjacentVertices.keySet();

        for (Vertex vertex : vertices) {
            StringBuilder line = new StringBuilder();
            line.append(vertex).append("->");

            for (Vertex v : adjacentVertices.get(vertex)) {
                line.append(v).append(" ");
            }

            stdout(line);
        }
    }

    /**
     * Stacks are used for LIFO processing like DFS search.
     * Depth First
     * @param root
     */
    public void traverseDFS(String root) {
        Stack<String> stack = new Stack<>();
        stack.push(root);
        printTab("DFS: -> ");

        while (!stack.isEmpty()) {
            String el = stack.pop();
            //Another way is to have a visited Set and check that. That has Space complexity. This approach has Time Complexity
            //But with Java 8+ processing is faster.
            Vertex rootVertex = getVertex(el);

            if (!rootVertex.visited) {
                rootVertex.visited = true;
                printTab(el);
            }

            List<Vertex> vertices = adjacentVertices.get(rootVertex);

            for (Vertex vertex : vertices) {
                if (!vertex.visited)  stack.push(vertex.name);
            }
        }

        print("\n");
    }

    /**
     * Queues are used in below algorithm for FIFO processing.
     * Breadth First
     * @param root
     */
    public void traverseBFS(String root) {
        Queue<String> queue = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        queue.offer(root);
        printTab("BFS: -> ");

        while (!queue.isEmpty()) {
            String el = queue.poll();

            if (!visited.contains(el)) {
                visited.add(el);
                printTab(el);
            }

            for (Vertex vertex: getVertices(el)) {
                if (!visited.contains(vertex.name)) queue.offer(vertex.name);
            }
        }

        print("\n");
    }

    /**
     * Print the path between nodes (inclusive)
     * @param start
     * @param end
     */
    public void printPath(String start, String end, StringBuilder path) {
        if (start.equalsIgnoreCase(end)) {
            stdout("Path is " + path.toString());
            return;
        }

        if (!path.toString().contains(start)) path.append(start).append("-");

        List<Vertex> vertices = getVertices(start);

        for (Vertex v: vertices) {
            if (!path.toString().contains(v.name)) {
                path.append(v.name).append("-");
                printPath(v.name, end, path);
                return;
            }
        }
    }
}
