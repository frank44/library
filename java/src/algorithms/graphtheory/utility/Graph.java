package algorithms.graphtheory.utility;

/**
 * General graph contract for all graph related algorithms to manipulate.
 * Note that you can remove edges but you can never removes nodes.
 */
public interface Graph {
    int getSize();
    void addDirectedEdge(int from, int to, int weight);
    void removeDirectedEdge(int from, int to);
    Iterable<Edge> getNeighbors(int nodeIndex);
}
