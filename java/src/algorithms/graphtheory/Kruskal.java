package algorithms.graphtheory;

import algorithms.graphtheory.utility.Edge;
import datastructures.DisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal's algorithm for computing the minimum spanning tree (MST) of a graph already in edge-set form. Uses O(E)
 * extra space and runs in O(Elog(E)) time.
 */
public class Kruskal {

    static List<Edge> getMST(int N, List<Edge> edgeSet) {
        if (edgeSet == null) {
            throw new NullPointerException("edgeSet is null");
        }
        Collections.sort(edgeSet);
        List<Edge> res = new ArrayList<>();
        DisjointSet ds = new DisjointSet();
        for (Edge e : edgeSet) {
            if (res.size() == N - 1) { // Optimization - stop after you've added N-1 edges
                break;
            }
            int set1 = ds.getRepresentative(e.to);
            int set2 = ds.getRepresentative(e.from);
            if (set1 == -1 || set2 == -1 || set1 != set2) {
                if (set1 == -1) {
                    ds.tryAddSet(e.to);
                    set1 = e.to;
                }
                if (set2 == -1) {
                    ds.tryAddSet(e.from);
                    set2 = e.from;
                }
                ds.merge(set1, set2);
                res.add(e);
            }
        }
        return res;
    }
}
