package edu.asu.cse464;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {
    //using sets so duplicate edges do not get added
    private final Set<String> nodes;
    private final Set<Edge> edges;

    public Graph() {
        //linkedHashSet keeps insertion order which makes output easier to read
        this.nodes = new LinkedHashSet<>();
        this.edges = new LinkedHashSet<>();
    }

    public boolean addNode(String label) {
        //invalid node labels should not be added
        if (label == null) {
            return false;
        }

        String cleaned = label.trim();

        if (cleaned.isEmpty()) {
            return false;
        }
        //returns false automaticaly if the node already exists
        return nodes.add(cleaned);
    }

    public void addNodes(String[] labels) {
        //if the array itself is null just do nothing
        if (labels == null) {
            return;
        }

        for (String label : labels) {
            addNode(label);
        }
    }

    public boolean addEdge(String srcLabel, String dstLabel) {
        //both source and destination needs to exist
        if (srcLabel == null || dstLabel == null) {
            return false;
        }

        String src = srcLabel.trim();
        String dst = dstLabel.trim();

        if (src.isEmpty() || dst.isEmpty()) {
            return false;
        }
        //making sure the nodes are in the graph before adding the edge
        addNode(src);
        addNode(dst);

        //returns false if the same directed edge is already there
        return edges.add(new Edge(src, dst));
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void removeNode(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Node label cannot be null.");
        }
        String cleaned = label.trim();
        if (cleaned.isEmpty()) {
            throw new IllegalArgumentException("Node label cannot be empty.");
        }
        if (!nodes.contains(cleaned)) {
            throw new IllegalArgumentException("Node does not exist: " + cleaned);
        }
        nodes.remove(cleaned);
        edges.removeIf(edge ->
                edge.getSource().equals(cleaned) || edge.getDestination().equals(cleaned));
    }

    public void removeNodes(String[] labels) {
        if (labels == null) {
            throw new IllegalArgumentException("Node label array cannot be null.");
        }
        for (String label : labels) {
            removeNode(label);
        }
    }

    public void removeEdge(String srcLabel, String dstLabel) {
        if (srcLabel == null || dstLabel == null) {
            throw new IllegalArgumentException("Edge labels cannot be null.");
        }

        String src = srcLabel.trim();
        String dst = dstLabel.trim();
        if (src.isEmpty() || dst.isEmpty()) {
            throw new IllegalArgumentException("Edge labels cannot be empty.");
        }
        Edge edgeToRemove = new Edge(src, dst);
        if (!edges.contains(edgeToRemove)) {
            throw new IllegalArgumentException("Edge does not exist: " + src + " -> " + dst);
        }
        edges.remove(edgeToRemove);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Number of nodes: ").append(nodes.size()).append(System.lineSeparator());

        sb.append("Nodes: ");
        if (nodes.isEmpty()) {
            sb.append("None");
        } else {
            //joins all node labels into one line separated by commas
            sb.append(nodes.stream().collect(Collectors.joining(", ")));
        }
        sb.append(System.lineSeparator());

        sb.append("Number of edges: ").append(edges.size()).append(System.lineSeparator());
        sb.append("Edges:").append(System.lineSeparator());

        if (edges.isEmpty()) {
            sb.append("None");
        } else {
            //prints each edge on its own line
            for (Edge edge : edges) {
                sb.append(edge).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}












