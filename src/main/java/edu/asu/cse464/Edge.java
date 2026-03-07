package edu.asu.cse464;

import java.util.Objects;

public class Edge {
    //stores where the edge starts from
    private final String source;

    //stores where the edge goes to
    private final String destination;

    //making one directed edge
    public Edge(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        //showing the edge in DOT style format
        return source + " -> " + destination;
    }

    @Override
    public boolean equals(Object o) {
        //if both references point to the same object they are obviously equal
        if (this == o) return true;

        //makes sure the object is actually an edge before comparing
        if (!(o instanceof Edge edge)) return false;

        //two edges are equal only if bot source and destination match
        return Objects.equals(source, edge.source) &&
                Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        //needed so duplicate edges can be handled properly in sets
        return Objects.hash(source, destination);
    }
}













