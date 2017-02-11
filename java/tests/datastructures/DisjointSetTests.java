package datastructures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DisjointSetTests {

    @Test
    public void sizeTest() {
        DisjointSet ds = new DisjointSet();
        assertEquals(0, ds.size());

        for (int i=0; i<5; i++) { // adding 5 singletons
            ds.tryAddSet(i);
        }
        assertEquals(5, ds.size());
        ds.tryAddSet(1); // adding a duplicate should fail and not affect the size
        assertEquals(5, ds.size());
        ds.merge(0, 1); // merging two singletons should reduce the size by 1
        assertEquals(4, ds.size());
    }
}
