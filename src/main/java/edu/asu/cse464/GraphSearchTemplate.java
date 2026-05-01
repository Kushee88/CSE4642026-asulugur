package edu.asu.cse464;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class GraphSearchTemplate {

    // storing graph so all search algos can use same data
    protected Graph graph;

    public GraphSearchTemplate(Graph graph) {
        this.graph = graph;
    }

    // main search logic used by bfs, dfs, and random walk
    public Path search(String src, String dst) {

        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dst)) {
            return null;
        }

        // initialize frontier based on search type
        List<Path> frontier = initFrontier(src);

        // track visited nodes so we dont revisit same node again
        Set<String> visited = new HashSet<>();

        while (!frontier.isEmpty()) {

            // get next path based on algo behavior
            Path currentPath = removeFrontier(frontier);

            // printing so random walk process can be shown
            System.out.println("Visit Node History: " + String.join("-", currentPath.getNodes()));

            // get last node in current path
            String lastNode = currentPath.getLastNode();

            // if destination reached return the path
            if (lastNode.equals(dst)) {
                System.out.println("Found target node: " + dst);
                return currentPath;
            }

            // process only if node not already visited
            if (!visited.contains(lastNode)) {
                visited.add(lastNode);

                // add next possible paths based on algorithm
                frontier.addAll(getNextPaths(currentPath, visited));
            }
        }

        // if no path found return null
        return null;
    }

    // default behavior for bfs and dfs is to add all neighbors
    protected List<Path> getNextPaths(Path currentPath, Set<String> visited) {
        List<Path> nextPaths = new ArrayList<>();
        String lastNode = currentPath.getLastNode();

        for (String neighbor : graph.getNeighbors(lastNode)) {
            if (!visited.contains(neighbor)) {
                List<String> newPathList = new ArrayList<>(currentPath.getNodes());
                newPathList.add(neighbor);
                nextPaths.add(new Path(newPathList));
            }
        }

        return nextPaths;
    }

    // bfs, dfs, and random walk implement these differently
    protected abstract List<Path> initFrontier(String src);

    protected abstract Path removeFrontier(List<Path> frontier);
}
