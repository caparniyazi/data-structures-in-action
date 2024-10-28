package com.caparniyazi.ds.collections.assignment;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Develop a program to maintain a list of homework assignments. When an assignment is assigned,
 * add it to the list, and when it is completed, remove it. You should keep track of the due date. Your
 * program should provide the following services:
 * 􀁲 Add a new assignment.
 * 􀁲 Remove an assignment.
 * 􀁲 Provide a list of the assignments in the order they were assigned.
 * 􀁲 Find the assignment(s) with the earliest due date.
 */
public class HomeworkList {
    // Data fields
    private final LinkedList<Assignment> theList = new LinkedList<>();

    // Methods
    public void add(Assignment assignment) {
        theList.add(assignment);
    }

    public void remove(Assignment assignment) {
        theList.remove(assignment);
    }

    public void showAssignments() {
        String message;
        int i = 1;
        for (Assignment assignment : theList) {
            message = "Assignment# " + (i++) + ": " + "\n" +
                    assignment.getDescription() + "\nDue date: " + assignment.getDueDate() + "\n";
            System.out.println(message);
        }
    }

    public Assignment findEarliest() {
        Assignment earliest = null;
        Assignment current;
        ListIterator<Assignment> listIterator = theList.listIterator();

        if (listIterator.hasNext()) {
            earliest = listIterator.next();

            while (listIterator.hasNext()) {
                current = listIterator.next();

                if (current.compareTo(earliest) < 0) {
                    earliest = current;
                }
            }
        }

        return earliest;
    }
}
