package datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FenwickTreeTests {
    private FenwickTree tree;

    @Before
    public void setup() {
        tree = new FenwickTree(100);
    }

    @Test
    public void testLeftSum() {
        assertEquals(0, tree.leftSum(8));
        tree.update(1, 1);
        assertEquals(1, tree.leftSum(1));
        assertEquals(1, tree.leftSum(8));
        tree.update(3, 5);
        assertEquals(1, tree.leftSum(2));
        assertEquals(6, tree.leftSum(3));
        assertEquals(6, tree.leftSum(8));
        tree.update(5, -2);
        assertEquals(1, tree.leftSum(1));
        assertEquals(6, tree.leftSum(4));
        assertEquals(4, tree.leftSum(6));
    }

    @Test
    public void readSingleTest() {
        for (int i=1; i<15; i++) {
            tree.update(i, i);
        }

        for (int i=1; i<15; i++) {
            assertEquals(i, tree.getSingle(i));
        }
    }

    @Test
    public void getTotalSumTest() {
        tree.update(2, 5);
        tree.update (3, 8);
        tree.update(5, -1);
        assertEquals(12, tree.getTotalSum());
    }

    @Test
    public void scaleTest() {
        tree.update(2, 5);
        tree.update (3, 8);
        tree.update(5, -1);
        tree.scale(2);
        assertEquals(24, tree.getTotalSum());
        tree.scale(3);
        assertEquals(30, tree.getSingle(2));
    }
}
