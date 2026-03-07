package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphFeature3Test {

    //checking that duplicate edges are not added again
    @Test
    public void testAddEdge() {
        Graph graph = new Graph();

        assertTrue(graph.addEdge("a", "b"));
        assertFalse(graph.addEdge("a", "b"));
        assertEquals(2, graph.getNodes().size());
        assertEquals(1, graph.getEdges().size());
    }
}






