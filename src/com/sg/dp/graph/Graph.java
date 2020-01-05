package com.sg.dp.graph;

import java.util.*;

import static com.sg.dp.log.Logger.*;

public class Graph {
    Map<Vertex, Map<Vertex, Integer>> adjacentVertices = new LinkedHashMap<>();

    public void addVertex(String name) {
        adjacentVertices.putIfAbsent(Flyweight.getVertex(name), new HashMap<>());
    }

    public void addEdge(String src, String dest, int weight) {
        Vertex source = Flyweight.getVertex(src);
        Vertex destination = Flyweight.getVertex(dest);
        adjacentVertices.get(source).put(destination, weight);
        adjacentVertices.get(destination).put(source, weight);
    }

    public Map<Vertex, Integer> getVertices(String name) {
        return adjacentVertices.get(Flyweight.getVertex(name));
    }

    public Vertex getVertex(String key) {
        Set<Vertex> vertices = adjacentVertices.keySet();
        return vertices.parallelStream().filter(v -> v.name.equalsIgnoreCase(key)).findFirst().get();
    }
}