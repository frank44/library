package algorithms.graphtheory;

import algorithms.graphtheory.utility.Edge;
import algorithms.graphtheory.utility.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Prim's algorithm which is used to compute the minimum spanning tree of a graph.
 * Runtime depends on the underlying Graph implementation used:
 *      AdjacencyListGraph: O(Elog(E))
 *
 * Returns a list of edges describing the minimum spanning tree.
 */
public class Prim {

    public static List<Edge> getMinimumSpanningTree(Graph graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(-1, 0, 0)); // Virtual edge to start off algorithm
        boolean[] seen = new boolean[graph.getSize()];

        List<Edge> mst = new ArrayList<>();
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (seen[cur.to]) {
                continue; // Already visited, ignore
            }

            seen[cur.to] = true;
            if (cur.from != -1) { // Add to result set (unless it's the virtual edge)
                mst.add(cur);
            }
            for (Edge e : graph.getNeighbors(cur.to)) {
                if (!seen[e.to]) {
                    pq.offer(e);
                }
            }
        }
        return mst;
    }
}
