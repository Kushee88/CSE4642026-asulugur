package edu.asu.cse464;

import java.util.ArrayList;
import java.util.List;

public class DFSStrategy extends GraphSearchTemplate {

    public DFSStrategy(Graph graph) {
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

    // DFS = remove from end (stack style behavior)
    @Override
    protected Path removeFrontier(List<Path> frontier) {
        return frontier.remove(frontier.size() - 1);
    }
}
