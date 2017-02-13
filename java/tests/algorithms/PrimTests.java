package algorithms;

import algorithms.graphtheory.Prim;
import algorithms.graphtheory.utility.AdjacencyListGraph;
import algorithms.graphtheory.utility.Edge;
import algorithms.graphtheory.utility.Graph;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class PrimTests {

    // Taken from https://www.hackerrank.com/challenges/primsmstsub
    // Sample test case
    @Test
    public void smallGraphTest() throws IOException {
        Graph g = getGraphFromFile("SmallGraphForPrimTest.txt"); // 5 nodes
        List<Edge> edgeSet = Prim.getMinimumSpanningTree(g);
        int sum = 0;
        for (Edge e : edgeSet) {
            sum += e.weight;
        }
        assertEquals(15, sum);
    }

    // Stress test taken from https://www.hackerrank.com/challenges/primsmstsub
    // Test case #5
    @Test
    public void largeGraphTest() throws IOException {
        Graph g = getGraphFromFile("LargeGraphForPrimTest.txt"); // 5 nodes
        List<Edge> edgeSet = Prim.getMinimumSpanningTree(g);
        int sum = 0;
        for (Edge e : edgeSet) {
            sum += e.weight;
        }
        assertEquals(6359060, sum);
    }

    private Graph getGraphFromFile(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource(fileName).getFile());
        Scanner in = new Scanner(inputFile);
        int numNodes = in.nextInt();
        int numEdges = in.nextInt();
        Graph g = new AdjacencyListGraph(numNodes);
        for (int i=0; i<numEdges; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int weight = in.nextInt();
            g.addDirectedEdge(from, to, weight);
            g.addDirectedEdge(to, from, weight);
        }
        return g;
    }
}
