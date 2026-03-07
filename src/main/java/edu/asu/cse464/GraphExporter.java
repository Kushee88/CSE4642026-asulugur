package edu.asu.cse464;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GraphExporter {

    public static void outputGraph(Graph graph, String filepath) throws IOException {
        //writing the graph summary from toString() into a text file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            writer.println(graph.toString());
        }
    }

    public static void outputDOTGraph(Graph graph, String filepath) throws IOException {
        //writing the graph back into DOT format so Graphviz can read it
        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            writer.println("digraph {");

            for (String node : graph.getNodes()) {
                //checking if this node is actually used in any edge
                boolean hasEdge = graph.getEdges().stream()
                        .anyMatch(edge -> edge.getSource().equals(node) || edge.getDestination().equals(node));

                //if the node is isolated writing it separately so it still shows up in the graph
                if (!hasEdge) {
                    writer.println("    " + node + ";");
                }
            }

            //writing all directed edges in DOT style
            for (Edge edge : graph.getEdges()) {
                writer.println("    " + edge.getSource() + " -> " + edge.getDestination() + ";");
            }

            writer.println("}");
        }
    }

    public static void outputGraphics(String dotFilePath, String outputFilePath, String format)
            throws IOException, InterruptedException {
        //running Graphviz from Java to generate the final graph image
        ProcessBuilder processBuilder = new ProcessBuilder(
                "C:\\Program Files\\Graphviz\\bin\\dot.exe",
                "-T" + format,
                dotFilePath,
                "-o",
                outputFilePath
        );

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        //if Graphviz fails throw an error instead of silently ignoring it
        if (exitCode != 0) {
            throw new IOException("Graphviz failed to generate output file.");
        }
    }
}




