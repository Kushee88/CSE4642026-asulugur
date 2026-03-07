package edu.asu.cse464;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphFeature4Test {

    //checking that the DOT output file gets created correctly
    @Test
    public void testOutputDOTGraph() throws Exception {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");

        String path = "src/main/resources/test-output.dot";
        GraphExporter.outputDOTGraph(graph, path);

        File file = new File(path);
        assertTrue(file.exists());
    }

    //checking that the text sumary file gets created too
    @Test
    public void testOutputTextGraph() throws Exception {
        Graph graph = new Graph();
        graph.addEdge("a", "b");

        String path = "src/main/resources/test-output.txt";
        GraphExporter.outputGraph(graph, path);

        File file = new File(path);
        assertTrue(file.exists());
    }
}




