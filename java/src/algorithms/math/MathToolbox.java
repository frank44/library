package algorithms.math;

import java.math.BigInteger;

/**
 * Collection of common math helper functions.
 *
 *      int gcd(int a, int b): Greatest common divisor. Supports both positive and negative numbers.
 *      long gcd(long a, long b)
 *      int modpow(int a, int b, int c): Returns (a^b)(mod c)
 *      long modpow(long a, long b, long c)
 *      BigInteger choose(int n, int k): Returns nCk, the number of ways of selecting 'k' objects from 'n'. Requires k
 *                                       BigInteger multiplications and divisions.
 *      long[][] precomputePascalsTriangle(int maxN): Returns a full Pascal triangle of the specified size. Beware the
 *                                                    table will start to overflow after maxN > 52.
 *      boolean isLeapYear(int year): Whether or not the specified year is a leap year
 */
public final class MathToolbox {

    // This class should not be instantiated.
    private MathToolbox() { }

    public static int gcd(int a, int b) {
        int sign = 1;
        if (a <= 0 && b <= 0) {
            sign = -1;
        }
        if (a < 0) {
            a = Math.abs(a);
        }
        if (b < 0) {
            b = Math.abs(b);
        }

        while(b > 0)
        {
            int c = a % b;
            a = b;
            b = c;
        }
        return sign * a;
    }

    public static long gcd(long a, long b) {
        long sign = 1L;
        if (a <= 0 && b <= 0) {
            sign = -1L;
        }
        if (a < 0) {
            a = Math.abs(a);
        }
        if (b < 0) {
            b = Math.abs(b);
        }

        while(b > 0)
        {
            long c = a % b;
            a = b;
            b = c;
        }
        return sign * a;
    }

    // Assumes: c < sqrt(Integer.MAX_VALUE) or ~65,000
    public static int modpow(int a, int b, int c) {
        a %= c;
        if (a == 0) {
            if (b == 0) {
                throw new IllegalArgumentException("Cannot compute 0^0");
            }
            return 0;
        }
        int result = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % c;
            }
            a = a * a % c;
            b >>= 1;
        }
        return result;
    }

    // Assumes: c < sqrt(Long.MAX_VALUE) or ~2*10^9
    public static long modpow(long a, long b, long c) {
        a %= c;
        if (a == 0) {
            if (b == 0) {
                throw new IllegalArgumentException("Cannot compute 0^0");
            }
            return 0;
        }
        long result = 1L;
        while (b > 0) {
            if (b % 2 == 1) {
                result = result * a % c;
            }
            a = a * a % c;
            b >>= 1;
        }
        return result;
    }

    public static BigInteger choose(int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            throw new IllegalArgumentException("Invalid nCk: " + n + "C" + k);
        }
        k = Math.min(k, n-k);

        // Uses nCr * (n - r) / (r + 1) = nC(r+1)
        // to incrementally compute nCr.
        BigInteger ret = BigInteger.ONE;
        BigInteger numerator = new BigInteger(n + "");
        BigInteger denominator = BigInteger.ONE;
        for (int i = 1; i <= k; i++) {
            ret = ret.multiply(numerator).divide(denominator);
            numerator = numerator.subtract(BigInteger.ONE);
            denominator = denominator.add(BigInteger.ONE);
        }

        return ret ;
    }

    // Overflow starts occurring after maxN > 52.
    public static long[][] precomputePascalsTriangle(int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException("Illegal N: " + maxN);
        }

        long[][] C = new long[maxN][maxN];
        for (int i = 0; i < maxN; i++)
        {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++)
                C[i][j] = C[i-1][j-1] + C[i-1][j];
        }
        return C;
    }

    // Leap years are either of the following:
    //   * Divisible by 400
    //   * Divisible by 4 and not divisible by 100.
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

}
