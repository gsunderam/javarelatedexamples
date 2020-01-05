package com.sg.dp.graph;

import java.util.*;
import static com.sg.dp.log.Logger.stdout;

public class DijkstraAlgorithm {
    private static final int INITIAL_WEIGHT = 100000;

    public static void printShortestPath(Graph graph, String start, String end) {
        Queue<VertexWeight> queue =  new PriorityQueue<>();
        initializeQueue(graph, start, queue);
        Set<String> visited = new HashSet<>();
        StringBuilder journey = new StringBuilder(start + "-");
        visited.add(start);
        stdout("Processing node " + start);
        String currentNode = "";
        int newDistance = 0;

        while (!currentNode.equalsIgnoreCase(end)) {
            VertexWeight vtxWgt = queue.poll();
            stdout("Vertices of " + vtxWgt.name);

            Map<Vertex, Integer> adjVerts = graph.getVertices(vtxWgt.name);
            for(Map.Entry<Vertex, Integer> edgeWeights : adjVerts.entrySet()) {
                if (!visited.contains(edgeWeights.getKey().name)) {
                    currentNode = edgeWeights.getKey().name;
                    visited.add(currentNode);
                    stdout("Processing node " + currentNode);
//                  getQueueElement(queue, edgeWeights.getKey());
                    VertexWeight queueEl = Flyweight.getVertexWeight(edgeWeights.getKey().name);
                    if (queueEl.weight > edgeWeights.getValue() + vtxWgt.weight) {
                        queue.remove(queueEl);
                        newDistance = edgeWeights.getValue() + vtxWgt.weight;
                        queue.offer(Flyweight.getVertexWeight(edgeWeights.getKey().name, newDistance));
                        journey.append(currentNode).append("-");
                        if (currentNode.equalsIgnoreCase(end)) break;
                    }
                }
            }
        }

        stdout("Min distance: " + newDistance);
        stdout(queue);
        stdout("Nodes visited: " + visited);
        stdout("Journey in the Graph: " + journey.toString());
    }

    private VertexWeight getQueueElement(Queue<VertexWeight> queue, Vertex key) {
        Optional<VertexWeight> queueElement = queue.parallelStream().filter(vertexWeight -> vertexWeight.name.equalsIgnoreCase(key.name)).findFirst();
        if (queueElement.isPresent()) {
            VertexWeight element = queueElement.get();
//            stdout("Returning Queue element " + element);
            return element;
        } else {
            stdout("Returning null for " + key.name);
        }

        return null;
    }

    private static void initializeQueue(Graph graph, String start, Queue<VertexWeight> queue) {
        Set<Vertex> allVertices = graph.adjacentVertices.keySet();
        for (Vertex vertex: allVertices) {
            if (vertex.name.equalsIgnoreCase(start)) {
                queue.add(Flyweight.getVertexWeight(start, 0));
            } else {
                queue.add(Flyweight.getVertexWeight(vertex.name, INITIAL_WEIGHT));
            }
        }

    }
}
