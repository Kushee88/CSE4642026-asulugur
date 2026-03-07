package edu.asu.cse464;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphParser {

    public static Graph parseGraph(String filepath) throws IOException {
        Graph graph = new Graph();

        //reading the DOT file line by line and buildig the graph
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                //skipping empty lines so they do not mess up parsing
                if (line.isEmpty()) {
                    continue;
                }

                //this line just declares the graph, so no need to add anything
                if (line.startsWith("digraph")) {
                    continue;
                }

                //skipping opening and closing braces
                if (line.equals("{") || line.equals("}")) {
                    continue;
                }

                //removing the semicoln at the end to make parsing easier
                if (line.endsWith(";")) {
                    line = line.substring(0, line.length() - 1).trim();
                }

                if (line.contains("->")) {
                    //if the line has -> then it is an edge
                    String[] parts = line.split("->");

                    if (parts.length == 2) {
                        String source = parts[0].trim();
                        String destination = parts[1].trim();
                        graph.addEdge(source, destination);
                    }
                } else {
                    //otherwise treating it like a standalone node
                    graph.addNode(line);
                }
            }
        }

        return graph;
    }
}
















