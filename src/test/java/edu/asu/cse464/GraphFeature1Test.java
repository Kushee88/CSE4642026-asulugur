package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphFeature1Test {

    //checking that the parser reads the input DOT file correctly
    @Test
    public void testParseGraph() throws IOException {
        Graph graph = GraphParser.parseGraph("src/main/resources/input.dot");

        assertEquals(4, graph.getNodes().size());
        assertEquals(3, graph.getEdges().size());
        assertTrue(graph.getNodes().contains("a"));
        assertTrue(graph.getNodes().contains("d"));
    }
}


