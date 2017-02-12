package algorithms.graphtheory;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Prim's algorithm which is used to compute the minimum spanning tree of a graph.
 */
public class Prim {

    static List<Edge> prims(List<List<Edge>> graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(-1, 0, 0)); // Virtual edge to start off algorithm
        boolean[] seen = new boolean[graph.size()];

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
            for (Edge e : graph.get(cur.to)) {
                if (!seen[e.to]) {
                    pq.offer(e);
                }
            }
        }
        return mst;
    }
}
