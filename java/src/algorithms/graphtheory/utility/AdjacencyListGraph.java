package algorithms.graphtheory.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph implementation backed by an adjacency list.
 */
public class AdjacencyListGraph implements Graph {
    private int N;
    private List<List<Edge>> graph;

    public AdjacencyListGraph(int graphSize) {
        N = graphSize;
        graph = new ArrayList<>(N);
        for (int i=0; i<N; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public int getSize() {
        return N;
    }

    public void addDirectedEdge(int from, int to, int weight) {
        if (!isValidNode(from) || !isValidNode(to)) {
            throw new IllegalArgumentException("Trying to add an edge with an illegal index: " + from + ", " + to);
        }
        graph.get(from).add(new Edge(from, to, weight));
    }

    public void removeDirectedEdge(int from, int to) {
        if (!isValidNode(from) || !isValidNode(to)) {
            throw new IllegalArgumentException("Trying to remove an edge with an illegal index: " + from + ", " + to);
        }
        graph.get(from).removeIf(edge -> edge.to == to);
    }

    public Iterable<Edge> getNeighbors(int nodeIndex) {
        if (!isValidNode(nodeIndex)) {
            throw new IllegalArgumentException("Invalid graph node specified: " + nodeIndex);
        }
        return graph.get(nodeIndex);
    }

    private boolean isValidNode(int i) {
        return 0 <= i && i < N;
    }
}
