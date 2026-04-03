package edu.asu.cse464;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphFeature5Test {

    @Test
    public void testRemoveSomeNodesAndSomeEdges() {
        Graph graph = new Graph();

        //adding spme nodes
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        //adding some edges between the nodes
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("A", "D");

        //removing one edge and one node
        graph.removeEdge("A", "D");
        graph.removeNode("B");

        //checking if node B is actualy removed
        assertFalse(graph.getNodes().contains("B"));

        //edges connected to B should also be removed
        assertFalse(graph.getEdges().contains(new Edge("A", "B")));
        assertFalse(graph.getEdges().contains(new Edge("B", "C")));

        //checking removed edge
        assertFalse(graph.getEdges().contains(new Edge("A", "D")));

        //other nodes should still exist
        assertTrue(graph.getNodes().contains("A"));
        assertTrue(graph.getNodes().contains("C"));
        assertTrue(graph.getNodes().contains("D"));

        //edge that should still exist
        assertTrue(graph.getEdges().contains(new Edge("C", "D")));
    }

    @Test
    public void testRemoveNonexistentNodeThrowsException() {
        Graph graph = new Graph();
        //adding some nodes
        graph.addNode("A");
        graph.addNode("B");
        //trying to remove a node that deosnt exist.
        assertThrows(IllegalArgumentException.class, () -> {
            graph.removeNode("Z");
        });
    }

    @Test
    public void testRemoveNonexistentNodesThrowsException() {
        Graph graph = new Graph();
        //adding some nodes
        graph.addNode("A");
        graph.addNode("B");
        //one valid and one invalid node -> should throw exception
        assertThrows(IllegalArgumentException.class, () -> {
            graph.removeNodes(new String[]{"A", "X"});
        });
    }

    @Test
    public void testRemoveNonexistentEdgeThrowsException() {
        Graph graph = new Graph();
        //adding nodes and one edge
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("A", "B");
        //trying to remove an edge that doesnt exist
        assertThrows(IllegalArgumentException.class, () -> {
            graph.removeEdge("B", "A");
        });
    }
}






















