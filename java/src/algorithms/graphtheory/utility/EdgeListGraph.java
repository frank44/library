package algorithms.graphtheory.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Graph implementation backed by an edge list.
 */
public class EdgeListGraph implements Graph {
    private int N;
    private List<Edge> graph;

    public EdgeListGraph() {
        graph = new ArrayList<>();
    }

    public int getSize() {
        return N;
    }

    public void addDirectedEdge(int from, int to, int weight) {
        if (N < from + 1) {
            N = from + 1;
        }
        if (N < to + 1) {
            N = to + 1;
        }
        graph.add(new Edge(from, to, weight));
    }

    public void removeDirectedEdge(int from, int to) {
        if (!isValidNode(from) || !isValidNode(to)) {
            throw new IllegalArgumentException("Trying to remove an edge with an illegal index: " + from + ", " + to);
        }
        graph.removeIf(edge -> edge.from == from && edge.to == to);
    }

    public Iterable<Edge> getNeighbors(int nodeIndex) {
        if (!isValidNode(nodeIndex)) {
            throw new IllegalArgumentException("Invalid graph node specified: " + nodeIndex);
        }
        List<Edge> neighbors = new ArrayList<>();
        for (Edge e : graph) { // Add all outgoing edges from nodeIndex
            if (e.from == nodeIndex) {
                neighbors.add(e);
            }
        }
        return neighbors;
    }

    private boolean isValidNode(int i) {
        return 0 <= i && i < N;
    }
}
