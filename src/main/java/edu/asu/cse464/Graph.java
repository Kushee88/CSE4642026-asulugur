package edu.asu.cse464;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {
    //using sets so duplicate edges do not get added
    private final Set<String> nodes;
    private final Set<Edge> edges;

    //used to choose which search algorithm to run
    public enum Algorithm {
        BFS, DFS
    }

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

        //returns false automatically if the node already exists
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
        //both source and destination need to exist
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

    //removes a single node from the graph
    public void removeNode(String label) {
        //null label not allowed
        if (label == null) {
            throw new IllegalArgumentException("Node label cannot be null.");
        }

        //cleaning input
        String cleaned = label.trim();

        //empty label not allowed
        if (cleaned.isEmpty()) {
            throw new IllegalArgumentException("Node label cannot be empty.");
        }

        //node must exist in graph
        if (!nodes.contains(cleaned)) {
            throw new IllegalArgumentException("Node does not exist: " + cleaned);
        }

        //remove node
        nodes.remove(cleaned);

        //remove all edges connected to this node
        edges.removeIf(edge ->
                edge.getSource().equals(cleaned) || edge.getDestination().equals(cleaned));
    }

    //removes multiple nodes from the graph
    public void removeNodes(String[] labels) {
        //array itself cannot be null
        if (labels == null) {
            throw new IllegalArgumentException("Node label array cannot be null.");
        }

        //remove each node one by one using removeNode()
        for (String label : labels) {
            removeNode(label);
        }
    }

    //removes a specific edge from the graph
    public void removeEdge(String srcLabel, String dstLabel) {
        //both labels must be provided
        if (srcLabel == null || dstLabel == null) {
            throw new IllegalArgumentException("Edge labels cannot be null.");
        }

        //cleaning input
        String src = srcLabel.trim();
        String dst = dstLabel.trim();

        //empty labels not allowed
        if (src.isEmpty() || dst.isEmpty()) {
            throw new IllegalArgumentException("Edge labels cannot be empty.");
        }

        //create edge object to check if it exists
        Edge edgeToRemove = new Edge(src, dst);

        //edge must exist before removing
        if (!edges.contains(edgeToRemove)) {
            throw new IllegalArgumentException("Edge does not exist: " + src + " -> " + dst);
        }

        //remove the edge
        edges.remove(edgeToRemove);
    }

    //BFS search
    public Path bfsSearch(String src, String dst) {
        //if either node doesn't exist, no path possible
        if (!nodes.contains(src) || !nodes.contains(dst)) {
            return null;
        }

        //queue for BFS traversal
        java.util.Queue<String> queue = new java.util.LinkedList<>();

        //to keep track of how we reached each node
        java.util.Map<String, String> parent = new java.util.HashMap<>();

        //to avoid visiting same node again
        java.util.Set<String> visited = new java.util.HashSet<>();

        queue.add(src);
        visited.add(src);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            //if we reached destination, build the path
            if (current.equals(dst)) {
                java.util.List<String> path = new java.util.ArrayList<>();
                String step = dst;

                //backtracking from destination to source
                while (step != null) {
                    path.add(0, step);
                    step = parent.get(step);
                }

                return new Path(path);
            }

            //explore neighbors
            for (Edge e : edges) {
                if (e.getSource().equals(current)) {
                    String neighbor = e.getDestination();

                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        parent.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }

        //no path found
        return null;
    }

    //DFS search
    public Path dfsSearch(String src, String dst) {
        //if either node doesn't exist, no path possible
        if (!nodes.contains(src) || !nodes.contains(dst)) {
            return null;
        }

        java.util.Set<String> visited = new java.util.HashSet<>();
        java.util.List<String> path = new java.util.ArrayList<>();

        if (dfsHelper(src, dst, visited, path)) {
            return new Path(path);
        }

        return null;
    }

    //helper method for DFS
    private boolean dfsHelper(String current, String dst,
                              java.util.Set<String> visited,
                              java.util.List<String> path) {
        visited.add(current);
        path.add(current);

        //if we reached destination
        if (current.equals(dst)) {
            return true;
        }

        //go through neighbors
        for (Edge e : edges) {
            if (e.getSource().equals(current)) {
                String next = e.getDestination();

                if (!visited.contains(next)) {
                    if (dfsHelper(next, dst, visited, path)) {
                        return true;
                    }
                }
            }
        }

        //if dead end, go back
        path.remove(path.size() - 1);
        return false;
    }

    //final search API that lets user choose bfs or dfs
    public Path GraphSearch(String src, String dst, Algorithm algo) {
        if (algo == Algorithm.BFS) {
            return bfsSearch(src, dst);
        }

        return dfsSearch(src, dst);
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







