package com.caparniyazi.ds.tree;

/**
 * The class PrintDocument is used to define documents to be printed on a printer.
 * The class has a getSize method that gives the number of bytes to be transmitted
 * to the printer and a getTimeStamp method that gets the time that the print job was submitted.
 * Instead of basing the ordering on document names,
 * we want to order the documents by a value that is a function of both size
 * and the waiting time of a document.
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

    public int getSize() {
        return size;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public int compareTo(PrintDocument other) {
        double leftValue = P1 * getSize() + P2 * getTimeStamp();
        double rightValue = P1 * other.getSize() + P2 * other.getTimeStamp();
        return Double.compare(leftValue, rightValue);
    }
}
