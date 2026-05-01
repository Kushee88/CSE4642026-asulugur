package edu.asu.cse464;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class GraphSearchTemplate {
    //storing graph so all search algos can use same data

    protected Graph graph;

    public GraphSearchTemplate(Graph graph) {
        this.graph = graph;
    }
    //main search logic used by bfs and dfs

    public Path search(String src, String dst) {

        //initialize frontier
        List<Path> frontier = initFrontier(src);
        Set<String> visited = new HashSet<>();

        while (!frontier.isEmpty()) {
            //get next path based on algo
            Path currentPath = removeFrontier(frontier);
            //get last node in current path
            String lastNode = currentPath.getLastNode();

            //if destination reached return the path
            if (lastNode.equals(dst)) {
                return currentPath;
            }

            //process only if node not already visited
            if (!visited.contains(lastNode)) {
                visited.add(lastNode);

                //go through all neighbors of current node
                for (String neighbor : graph.getNeighbors(lastNode)) {

                    //create new path by copying current path and adding neigjbor
                    List<String> newPathList = new ArrayList<>(currentPath.getNodes());
                    newPathList.add(neighbor);

                    //add new path to frontier
                    frontier.add(new Path(newPathList));
                }
            }
        }
        return null;
    }

    protected abstract List<Path> initFrontier(String src);

    protected abstract Path removeFrontier(List<Path> frontier);
}
