package edu.asu.cse464;

import java.util.ArrayList;
import java.util.List;

public class BFSStrategy extends GraphSearchTemplate {

    public BFSStrategy(Graph graph) {
        super(graph);
    }

    // initialize with starting node
    @Override
    protected List<Path> initFrontier(String src) {
        List<Path> frontier = new ArrayList<>();

        List<String> start = new ArrayList<>();
        start.add(src);

        frontier.add(new Path(start));
        return frontier;
    }

    // BFS = remove from front (queue behavior)
    @Override
    protected Path removeFrontier(List<Path> frontier) {
        return frontier.remove(0);
    }
}
