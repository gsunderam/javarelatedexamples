package com.sg.dp.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Flyweight {
    private static final Map<String, Vertex> flyweight = new HashMap<>();

    public static Map<String, Vertex> getFlyweight() {
        return Collections.unmodifiableMap(flyweight);
    }

    public static Vertex getVertex(String name) {
        Vertex vertex = flyweight.get(name);

        if (vertex == null) {
            vertex = new Vertex(name);
            flyweight.putIfAbsent(name, vertex);
        }

        return vertex;
    }
}
