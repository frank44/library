package algorithms.graphtheory;

public class Edge implements Comparable<Edge> {
    public final int from;
    public final int to;
    public final int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        if (weight != other.weight) {
            return weight - other.weight;
        }

        if (from != other.from) {
            return from - other.from;
        }
        return to - other.to;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d)", from, to, weight);
    }
}