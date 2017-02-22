package algorithms.math;

import org.junit.Test;

import java.math.BigInteger;

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

    @Test
    public void modpowTests() {
        assertEquals(625409761L, MathToolbox.modpow(6269977260L,10287105L,1000000007L));
        assertEquals(7253, MathToolbox.modpow(30872,10287105,9997));
    }

    @Test
    public void chooseTest() {
        assertEquals(BigInteger.ONE, MathToolbox.choose(0,0));
        assertEquals(BigInteger.ONE, MathToolbox.choose(1,0));
        assertEquals(BigInteger.ONE, MathToolbox.choose(1,1));
        assertEquals(new BigInteger("512"), MathToolbox.choose(512,1));
        assertEquals(new BigInteger("10"), MathToolbox.choose(5, 2));
        assertEquals(new BigInteger("324618308499160805680005"), MathToolbox.choose(208, 192));
        assertEquals(new BigInteger("324618308499160805680005"), MathToolbox.choose(208, 208-192));
    }

    @Test
    public void precomputePascalsTriangleTest() {
        long[][] C = MathToolbox.precomputePascalsTriangle(20);
        assertEquals(1, C[0][0]);
        assertEquals(1, C[1][0]);
        assertEquals(1, C[1][1]);
        assertEquals(10, C[5][2]);
        assertEquals(70, C[8][4]);
        assertEquals(31824, C[18][7]);
        assertEquals(455, C[15][12]);
    }

    @Test
    public void isLeapYearTests() {
        assertEquals(true, MathToolbox.isLeapYear(2016));
        assertEquals(true, MathToolbox.isLeapYear(2000));
        assertEquals(false, MathToolbox.isLeapYear(1900));
        assertEquals(false, MathToolbox.isLeapYear(2017));
    }
}
