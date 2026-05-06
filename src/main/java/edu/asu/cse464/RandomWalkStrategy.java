package edu.asu.cse464;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomWalkStrategy extends GraphSearchTemplate {

    // used to randomly pick next node
    private final Random random = new Random();

    public RandomWalkStrategy(Graph graph) {
        super(graph);
    }

    // initialize with starting node
    @Override
    protected List<Path> initFrontier(String src) {
        List<Path> frontier = new ArrayList<>();

        // start path only has source node
        List<String> start = new ArrayList<>();
        start.add(src);

        frontier.add(new Path(start));
        return frontier;
    }

    // only one path is kept so just remove first element
    @Override
    protected Path removeFrontier(List<Path> frontier) {
        return frontier.remove(0);
    }

    // instead of exploring all neighbors like bfs/dfs
    // we pick only one random neighbor
    @Override
    protected List<Path> getNextPaths(Path currentPath, Set<String> visited) {

        List<Path> nextPaths = new ArrayList<>();

        // get last node of current path
        String lastNode = currentPath.getLastNode();

        // store valid neighbors (not visited)
        List<String> neighbors = new ArrayList<>();

        for (String neighbor : graph.getNeighbors(lastNode)) {
            if (!visited.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        // if no neighbors left just return empty
        if (neighbors.isEmpty()) {
            return nextPaths;
        }

        // pick one random neighbor
        String randomNeighbor = neighbors.get(random.nextInt(neighbors.size()));

        // create new path by adding that neighbor
        List<String> newPathList = new ArrayList<>(currentPath.getNodes());
        newPathList.add(randomNeighbor);

        nextPaths.add(new Path(newPathList));
        return nextPaths;
    }
}
