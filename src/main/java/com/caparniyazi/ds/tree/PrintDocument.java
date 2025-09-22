package com.caparniyazi.ds.tree;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The class PrintDocument is used to define documents to be printed on a printer.
 * The class has a getSize method that gives the number of bytes to be transmitted
 * to the printer and a getTimeStamp method that gets the time that the print job was submitted.
 * Instead of basing the ordering on document names,
 * we want to order the documents by a value that is a function of both size
 * and the waiting time of a document.
 * <p/>
 * If we were to use either time or size alone, small documents
 * could be delayed while big ones are printed, or big documents would never be printed.
 * By using a priority value that is a combination, we achieve a balanced usage of the printer.
 */
public class PrintDocument implements Comparable<PrintDocument> {
    // Data fields
    private final long timeStamp;
    private final int size;
    private static final double P1 = 0.8;
    private static final double P2 = 0.2;

    // Constructors
    public PrintDocument(int size, long timeStamp) {
        this.size = size;
        this.timeStamp = timeStamp;
    }

    /**
     * Method that gives the number of bytes to be transmitted to the printer.
     *
     * @return The number of bytes to be transmitted to the printer.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method that gets the time that the print job was submitted.
     *
     * @return the time that the print job was submitted.
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public int compareTo(PrintDocument other) {
        double leftValue = P1 * getSize() + P2 * getTimeStamp();
        double rightValue = P1 * other.getSize() + P2 * other.getTimeStamp();
        return Double.compare(leftValue, rightValue);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("timeStamp", timeStamp)
                .append("size", size)
                .toString();
    }
}
