package algorithms.math;

/**
 * Collection of common math helper functions.
 *
 *      int gcd(int a, int b): Greatest common divisor. Supports both positive and negative numbers.
 *      long gcd(long a, long b)
 *      int modpow(int a, int b, int c): Returns (a^b)(mod c)
 *      long modpow(long a, long b, long c)
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
}
