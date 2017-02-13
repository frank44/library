package algorithms.math;

/**
 * Collection of common math helper functions.
 *
 *      int gcd(int a, int b): Greatest common divisor. Supports both positive and negative numbers.
 *      long gcd(long a, long b)
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
}
