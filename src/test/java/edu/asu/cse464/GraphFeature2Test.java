package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphFeature2Test {

    //making sure the same node does not get added twice
    @Test
    public void testAddSingleNode() {
        Graph graph = new Graph();

        assertTrue(graph.addNode("x"));
        assertFalse(graph.addNode("x"));
        assertEquals(1, graph.getNodes().size());
    }

    //checking that addNodes handles multiple values and ignores duplicates
    @Test
    public void testAddMultipleNodes() {
        Graph graph = new Graph();

        graph.addNodes(new String[]{"a", "b", "c", "a"});

        assertEquals(3, graph.getNodes().size());
    }
}


