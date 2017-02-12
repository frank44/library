package algorithms.math;

import java.math.BigInteger;

public class BigFraction implements Comparable<BigFraction> {
    public BigInteger n, m;

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

    void reduce() {
        BigInteger gcd = n.gcd(m);
        if (!gcd.equals(BigInteger.ONE)) {
            n = n.divide(gcd);
            m = m.divide(gcd);
        }
    }

    public BigFraction multiply(BigFraction other) {
        // Try to divide early to keep numbers smaller
        BigInteger gcd1 = n.gcd(other.m);
        BigInteger gcd2 = m.gcd(other.n);
        return new BigFraction(n.divide(gcd1).multiply(other.n.divide(gcd2)), m.divide(gcd2).multiply(other.m.divide(gcd1)));
        // naive multiplication :: return new Fraction(n.multiply(other.n), m.multiply(other.m));
    }

    public BigFraction add(BigFraction other) {
        BigInteger nume;
        BigInteger deno;
        if (m.equals(other.m)) {
            nume = n.add(other.n);
            deno = m;
        } else {
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
        if (other.n.equals(BigInteger.ZERO)) {
            System.err.println("uh oh Fraction is trying to divide by zero");
            System.exit(1);
        }
        return this.multiply(other.inverse());
    }

    public int compareTo(BigFraction other) {
        return n.multiply(other.m).compareTo(m.multiply(other.n));
    }

    public boolean equals(BigFraction other) {
        return n.equals(other.n) && m.equals(other.m);
    }

    public String toString() {
        return n + " / " + m;
    }

    // The following are stateful methods:
}
