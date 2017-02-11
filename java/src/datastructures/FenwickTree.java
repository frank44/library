package datastructures;

/**
 * Minimal viable Iiplementation of a FenwickTree as described on:
 * https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
 * TODO - Implement more powerful operations
 */
public class FenwickTree {
    public final int MAX_SIZE;
    private int[] tree;

    public FenwickTree(int maxSize) {
        MAX_SIZE = maxSize;
        tree = new int[MAX_SIZE];
    }

    public int read(int inx) {
        int sum = 0;
        while (inx > 0) {
            sum += tree[inx];
            inx -= (inx & -inx);
        }
        return sum;
    }

    public void update(int inx, int val) {
        while (inx < MAX_SIZE) {
            tree[inx] += val;
            inx += (inx & -inx);
        }
    }
}
