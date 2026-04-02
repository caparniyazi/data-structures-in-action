package codingpractice;

/**
 * Represents a challenge with two factors.
 */
public final class Challenge {
    // Data fields
    private final int factorA;
    private final int factorB;

    public Challenge(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }

    public int getFactorA() {
        return factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (null != obj && (obj.getClass() == getClass())) {
            final Challenge other = (Challenge) obj;
            return factorA == other.getFactorA() && factorB == other.getFactorB();
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + factorA;
        result = 43 * result + factorB;

        return result;
    }

    @Override
    public String toString() {
        return "Challenge [factorA=" + factorA + ", factorB=" + factorB + "]";
    }
}
