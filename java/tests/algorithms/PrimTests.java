package algorithms;

import algorithms.graphtheory.Prim;
import algorithms.graphtheory.utility.AdjacencyListGraph;
import algorithms.graphtheory.utility.Edge;
import algorithms.graphtheory.utility.EdgeListGraph;
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
    public void smallAdjacencyListGraphTest() throws IOException {
        String fileName = "SmallGraphForPrimTest.txt";
        Graph g = new AdjacencyListGraph(5);
        getGraphFromFile(fileName, g); // 5 nodes

        List<Edge> edgeSet = Prim.getMinimumSpanningTree(g);
        assertEquals(15, sumWeights(edgeSet));
    }

    @Test
    public void smallEdgeListGraphTest() throws IOException {
        String fileName = "SmallGraphForPrimTest.txt";
        Graph g2 = new EdgeListGraph();
        getGraphFromFile(fileName, g2); // 5 nodes
        List<Edge> edgeSet = Prim.getMinimumSpanningTree(g2);
        assertEquals(15, sumWeights(edgeSet));
    }

    // Stress test taken from https://www.hackerrank.com/challenges/primsmstsub
    // Test case #5
    @Test
    public void largeAdjacencyListGraphTest() throws IOException {
        Graph g = new AdjacencyListGraph(1000);
        getGraphFromFile("LargeGraphForPrimTest.txt", g); // 1000 nodes
        List<Edge> edgeSet = Prim.getMinimumSpanningTree(g);
        assertEquals(6359060, sumWeights(edgeSet));
    }

    @Test
    public void largeEdgeListGraphTest() throws IOException {
        Graph g = new EdgeListGraph();
        getGraphFromFile("LargeGraphForPrimTest.txt", g); // 1000 nodes
        List<Edge> edgeSet = Prim.getMinimumSpanningTree(g);
        assertEquals(6359060, sumWeights(edgeSet));
    }

    private void getGraphFromFile(String fileName, Graph output) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource(fileName).getFile());
        Scanner in = new Scanner(inputFile);
        in.nextInt(); // Eat up the number of nodes.
        int numEdges = in.nextInt();
        for (int i = 0; i < numEdges; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int weight = in.nextInt();
            output.addDirectedEdge(from, to, weight);
            output.addDirectedEdge(to, from, weight);
        }
    }

    private int sumWeights(List<Edge> edgeSet) {
        int sum = 0;
        for (Edge e : edgeSet) {
            sum += e.weight;
        }
        return sum;
    }
}
