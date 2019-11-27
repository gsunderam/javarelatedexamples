package com.sg.dp.graph;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Flyweight design pattern to store objects in a cache and dispense when needed by the clients.
 * Factory to create "new" objects in lieu of the clients creating them
 * This reduces memory footprint and pauses due to GC.
 */
public final class Flyweight {
    private static final Map<String, Vertex> flyweight = new ConcurrentHashMap<>();

    public static Vertex getVertex(String name) {
        Vertex vertex = flyweight.get(name);

        if (vertex == null) {
            vertex = new Vertex(name);
            flyweight.putIfAbsent(name, vertex);
        }

        return vertex;
    }
}
