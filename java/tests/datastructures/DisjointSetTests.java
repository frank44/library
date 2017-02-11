package datastructures;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class DisjointSetTests {

    private DisjointSet mDisjointSet;

    @Before
    public void setup() {
        mDisjointSet = new DisjointSet();
    }

    @Test
    public void sizeTest() {
        assertEquals(0, mDisjointSet.size());

        for (int i = 0; i < 5; i++) { // adding 5 singletons
            mDisjointSet.tryAddSet(i);
        }
        assertEquals(5, mDisjointSet.size());
        mDisjointSet.tryAddSet(1); // adding a duplicate should fail and not affect the size
        assertEquals(5, mDisjointSet.size());
        mDisjointSet.merge(0, 1); // merging two singletons should reduce the size by 1
        assertEquals(4, mDisjointSet.size());
    }

    @Test
    public void containsTest() {
        for (int i = 0; i < 5; i += 2) {
            mDisjointSet.tryAddSet(i);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(i % 2 == 0, mDisjointSet.contains(i));
        }
    }

    @Test
    public void getRepresentativeTest() {
        int rep = addClumpOfKeys(0, 9);
        assertEquals(1, mDisjointSet.getRepresentativeKeySet().size());
        for (int i = 0; i < 10; i++) {
            assertEquals(rep, mDisjointSet.getRepresentative(i));
        }

        assertEquals(-1, mDisjointSet.getRepresentative(13));
        assertEquals(-1, mDisjointSet.getRepresentative(-7));
    }

    @Test
    public void mergeTest() {
        int a = addClumpOfKeys(0, 9);
        int b = addClumpOfKeys(10, 19);
        assertNotEquals(a, b);
        mDisjointSet.merge(a, b);

        assertEquals(mDisjointSet.getRepresentative(a),
                mDisjointSet.getRepresentative(b));
    }

    @Test
    public void getRepresentativeKeySetTest() {
        int a = addClumpOfKeys(0, 9);
        int b = addClumpOfKeys(10, 19);
        int c = addClumpOfKeys(20, 20);
        Set<Integer> keySet = mDisjointSet.getRepresentativeKeySet();
        assertEquals(3, keySet.size());
        assertTrue(keySet.contains(a));
        assertTrue(keySet.contains(b));
        assertTrue(keySet.contains(c));
    }

    // Helper function that adds a clump of connected TreeNodes to the disjoint set.
    // Returns the representative of that clump.
    private int addClumpOfKeys(int a, int b) {
        for (int i = a; i <= b; i++) {
            mDisjointSet.tryAddSet(i);
            mDisjointSet.merge(a, i); // merge everything together
        }
        return mDisjointSet.getRepresentative(a);
    }
}
