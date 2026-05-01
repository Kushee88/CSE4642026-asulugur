package edu.asu.cse464;

import java.util.List;

public class Path {

    //stores the path as a list of node labels
    private final List<String> nodes;

    public Path(List<String> nodes) {
        //saving the path passed from BFS/DFS
        this.nodes = nodes;
    }

    public List<String> getNodes() {
        //return the list of nodes in the path
        return nodes;
    }

    //(Refactor 1)
    public String getLastNode() {
        return nodes.get(nodes.size() - 1);
    }

    @Override
    public String toString() {
        //prints path in format: A-> B-> C
        return String.join(" -> ", nodes);
    }
}
