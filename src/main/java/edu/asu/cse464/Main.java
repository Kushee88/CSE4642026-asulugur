package edu.asu.cse464;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            //input file and all output file path
            String inputPath = "src/main/resources/demo-input.dot";
            String textOutputPath = "src/main/resources/output.txt";
            String dotOutputPath = "src/main/resources/output.dot";
            String pngOutputPath = "src/main/resources/output.png";

            //parsing the input DOT file into my graph object
            Graph graph = GraphParser.parseGraph(inputPath);

            //printing the graph summary in the console
            System.out.println(graph);

            //generating all required output files
            GraphExporter.outputGraph(graph, textOutputPath);
            GraphExporter.outputDOTGraph(graph, dotOutputPath);
            GraphExporter.outputGraphics(dotOutputPath, pngOutputPath, "png");

            System.out.println("Graph files generated successfully.");

            System.out.println();
            System.out.println("BFS from a to h:");
            graph.GraphSearch("a", "h", Graph.Algorithm.BFS);

            System.out.println();
            System.out.println("DFS from a to h:");
            graph.GraphSearch("a", "h", Graph.Algorithm.DFS);

            System.out.println();
            System.out.println("Random Walk from a to h:");

            //running random walk multiple times to show different random paths
            for (int i = 1; i <= 7; i++) {
                System.out.println("Random run " + i + ":");
                graph.GraphSearch("a", "h", Graph.Algorithm.RANDOM_WALK);
                System.out.println();
            }

        } catch (IOException | InterruptedException e) {
            //catches file/Graphviz related errors so the program does not crash silently
            System.out.println("Error: " + e.getMessage());
        }
    }
}
