package algorithms.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for MathToolbox
 */
public class MathToolboxTests {
    @Test
    public void gcdTests() {
        assertEquals(0, MathToolbox.gcd(0,0));
        assertEquals(3, MathToolbox.gcd(30, 27));
        assertEquals(888, MathToolbox.gcd(888, 888));
        assertEquals(1, MathToolbox.gcd(-1, 1));
        assertEquals(-9, MathToolbox.gcd(-9, -27));
        assertEquals(9, MathToolbox.gcd(-9, 27));
        assertEquals(8, MathToolbox.gcd(8, 32));
        assertEquals(1, MathToolbox.gcd(31, 997));
        assertEquals(1, MathToolbox.gcd(2, -3));

        // long equivalent tests
        assertEquals(0L, MathToolbox.gcd(0L,0L));
        assertEquals(3L, MathToolbox.gcd(30L, 27L));
        assertEquals(888L, MathToolbox.gcd(888L, 888L));
        assertEquals(1L, MathToolbox.gcd(-1L, 1L));
        assertEquals(-9L, MathToolbox.gcd(-9L, -27L));
        assertEquals(9L, MathToolbox.gcd(-9L, 27L));
        assertEquals(8L, MathToolbox.gcd(8L, 32L));
        assertEquals(1L, MathToolbox.gcd(31L, 997L));
        assertEquals(1L, MathToolbox.gcd(2L, -3L));
    }
}
