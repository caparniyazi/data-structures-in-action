package com.caparniyazi.ds.maps;

import java.util.Date;

/**
 * Implementing hash code for user-defined types.
 * The {@code Transaction} class is an immutable data type to encapsulate a
 * *  commercial transaction with a customer name, date, and amount.
 *
 * @author Robert Sedgewick
 */
public final class Transaction implements Comparable<Transaction> {
    // Data fields
    private final String who;   // customer
    private final Date when;    // date
    private final double amount;    // amount

    /**
     * @param who    The person involved in this transaction.
     * @param when   The date of this transaction.
     * @param amount The amount of this transaction.
     * @throws IllegalArgumentException if {@code amount}
     *                                  is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY},
     *                                  or {@code Double.NEGATIVE_INFINITY}
     */

    public Transaction(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }


    /**
     * Returns a string representation of this transaction.
     *
     * @return a string representation of this transaction
     */
    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    /**
     * Compares two transactions by amount.
     *
     * @param that the other transaction
     * @return { a negative integer, zero, a positive integer}, depending
     * on whether the amount of this transaction is { less than,
     * equal to, or greater than } the amount of that transaction
     */
    @Override
    public int compareTo(Transaction that) {
        return Double.compare(this.amount, that.amount);
    }

    /**
     * Compares this transaction to the specified object.
     *
     * @param other the other transaction
     * @return true if this transaction is equal to {@code other}; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Transaction that = (Transaction) other;
        return (this.amount == that.amount) && (this.who.equals(that.who))
                && (this.when.equals(that.when));
    }

    /**
     * Returns a hash code for this transaction.
     *
     * @return a hash code for this transaction
     */
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + who.hashCode();  // For reference types use hashCode()
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();    // For primitive types use hashCode() of wrapper type.
        return hash;
        // return Objects.hash(who, when, amount);
    }
}
