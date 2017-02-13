package algorithms.math;

import java.math.BigInteger;

/**
 * Immutable fraction class with support for arbitrarily large numbers.
 *
 * Operations supported:
 *      BigFraction add(BigFraction other)
 *      BigFraction subtract(BigFraction other)
 *      BigFraction multiply(BigFraction other)
 *      BigFraction divide()
 *      BigFraction negate(): Returns -this.
 *      BigFraction inverse(): Returns this^(-1)
 *      int signum(): Returns 1 if positive, -1 if negative, 0 otherwise.
 *      boolean equals(Object other)
 *      String toString()
 *      BigInteger getNumerator()
 *      BigInteger getDenominator()
 *
 *   TODO - Implement support for a^b
 */
public class BigFraction implements Comparable<BigFraction> {
    public static final BigFraction ZERO = new BigFraction(0, 1);
    private BigInteger n;
    private BigInteger m;

    public BigFraction(String n, String m) {
        this(new BigInteger(n), new BigInteger(m));
    }

    public BigFraction(long n, long m) {
        this(BigInteger.valueOf(n), BigInteger.valueOf(m));
    }

    public BigFraction(BigInteger n, BigInteger m) {
        if (BigInteger.ZERO.equals(m)) {
            throw new IllegalArgumentException("BigFraction with 0 denominator");
        }
        this.n = n;
        if (BigInteger.ZERO.equals(n)) {
            this.m = BigInteger.ONE;
        } else {
            this.m = m;
        }
        reduce();

        // Force negative to be on the numerator
        if (this.m.signum() == -1) {
            this.n = this.n.negate();
            this.m = this.m.negate();
        }
    }

    private void reduce() {
        BigInteger gcd = n.gcd(m);
        if (!gcd.equals(BigInteger.ONE)) {
            n = n.divide(gcd);
            m = m.divide(gcd);
        }
        if (n.equals(BigInteger.ZERO)) { // Normalize 0/d to 0/1.
            m = BigInteger.ONE;
        }
    }

    public int signum() {
        int cmp = this.compareTo(ZERO);
        if (cmp > 0) {
            return 1;
        } else if (cmp < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public BigFraction multiply(BigFraction other) {
        // Try to divide early to keep numbers smaller
        BigInteger gcd1 = n.gcd(other.m);
        BigInteger gcd2 = m.gcd(other.n);
        return new BigFraction(n.divide(gcd1).multiply(other.n.divide(gcd2)),
                m.divide(gcd2).multiply(other.m.divide(gcd1)));
    }

    public BigFraction add(BigFraction other) {
        BigInteger nume;
        BigInteger deno;
        if (m.equals(other.m)) {
            nume = n.add(other.n);
            deno = m;
        } else {
            // TODO - Use the LCM as the denominator instead
            nume = n.multiply(other.m).add(m.multiply(other.n));
            deno = m.multiply(other.m);
        }

        return new BigFraction(nume, deno);
    }

    public BigFraction subtract(BigFraction other) {
        return this.add(other.negate());
    }

    public BigFraction negate() {
        return new BigFraction(n.negate(), m);
    }

    public BigFraction inverse() {
        return new BigFraction(m, n);
    }

    public BigFraction divide(BigFraction other) {
        if (other.equals(ZERO)) {
            throw new IllegalArgumentException("Dividing by zero in BigFraction");
        }
        return this.multiply(other.inverse());
    }

    public int compareTo(BigFraction other) {
        return n.multiply(other.m).compareTo(m.multiply(other.n));
    }

    public boolean equals(Object other) {
        if (this == other) { // Exactly the same object
            return true;
        }

        if (other == null || !(other instanceof BigFraction)) {
            return false;
        }
        BigFraction otherFraction = (BigFraction) other;
        return n.equals(otherFraction.n) && m.equals(otherFraction.m);
    }

    public String toString() {
        return n + " / " + m;
    }

    public BigInteger getNumerator() {
        return n;
    }

    public BigInteger getDenominator() {
        return m;
    }
}
