package datastructures;

/**
 * Minimal viable implementation of a Fenwick tree as described on:
 * https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
 *
 * TLDR; A tree data structure that can efficiently read/update frequency cumulative tables. It can read and update
 * frequencies in O(log(MAX_SIZE)) time. It uses O(MAX_SIZE) additional space.
 *
 * Supported operations:
 *      void update(int inx, int delta) - Adds delta the frequency at index inx. O(log(MAX_SIZE)).
 *      int sumLeftTail(int inx) - Returns the sum of all frequencies with with indices in [1, inx]. O(log(MAX_SIZE)).
 *      int sumRightTail(int inx) - Returns the sum of all frequencies with with indices in [inx, MAX_SIZE).
 *                                  Runs in O(log(MAX_SIZE)).
 *      int readSingle(int inx) - Efficiently returns the frequency stored at index inx. This is roughly 2x faster than
 *                                computing sumLeftTail(inx) - sumLeftTail(inx - 1).
 *      int getTotalSum() - Returns the summation of all frequencies (0, MAX_SIZE). This is useful when trying to
 *                          determine the sum of certain ranges, e.g. [1, inx) which is equal to
 *                          TotalSum - sumOfRightTail(inx). O(1)
 *      void scale(double c) - Scale all frequencies by the specified multiplier. Beware of integer division here.
 *
 * Limitations:
 *      Index zero is not supported. Do not try to read(0) or update(0, delta).
 *      Cumulative frequencies could possibly overflow an int. This could easily be extended to longs if needed.
 */
public class FenwickTree {
    public final int MAX_SIZE;
    private int[] tree;
    private int totalSum = 0;

    public FenwickTree(int maxSize) {
        MAX_SIZE = maxSize;
        tree = new int[MAX_SIZE];
    }

    public int sumLeftTail(int inx) {
        int sum = 0;
        while (inx > 0) {
            sum += tree[inx];
            inx -= (inx & -inx);
        }
        return sum;
    }

    public int sumRightTail(int inx) {
        int sum = 0;
        while (inx < MAX_SIZE) {
            sum += tree[inx];
            inx += (inx & -inx);
        }
    }

    public void update(int inx, int delta) {
        totalSum += delta;
        while (inx < MAX_SIZE) {
            tree[inx] += delta;
            inx += (inx & -inx);
        }
    }

    public int getTotalSum() {
        return totalSum;
    }

    public int readSingle(int inx) {
        int sum = tree[inx]; // Sum will be decreased
        if (inx > 0) { // Special case
            int z = inx - (inx & -inx); // make z first
            inx--; // inx is no important any more, so instead y, you can use inx
            while (inx != z) { // At some iteration idx (y) will become z
                sum -= tree[inx];
                // Subtract tree frequency which is between y and "the same path"
                inx -= (inx & -inx);
            }
        }
        return sum;
    }

    public void scale(double c){
        for (int i = 1 ; i < MAX_SIZE ; i++) {
            tree[i] = (int) (tree[i] * c);
        }
    }
}
