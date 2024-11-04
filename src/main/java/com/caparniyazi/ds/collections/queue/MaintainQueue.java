package com.caparniyazi.ds.collections.queue;

import java.util.*;

/**
 * Class to maintain queue of customers.
 *
 * @pre Customers have been created.
 * @post Customers are modified based on user selections.
 */
public class MaintainQueue {
    // Data fields
    private final Queue<String> customers;
    private final Scanner in;

    // Constructors
    public MaintainQueue() {
        customers = new LinkedList<String>();
        in = new Scanner(System.in);
    }

    public void processCustomers() {
        String choice = "";
        String[] choices = {"add", "peek", "remove", "size", "position", "quit"};

        // Perform operations
        while (!choice.equalsIgnoreCase("quit")) {
            // Process the current choice.
            try {
                String name;
                System.out.println("Choose from the list: " + Arrays.toString(choices));
                choice = in.nextLine();

                switch (choice) {
                    case "add" -> {
                        System.out.println("Enter new customer name: ");
                        name = in.nextLine();
                        customers.offer(name);
                        System.out.println("Customer " + name + " added to the queue.");
                    }
                    case "peek" -> {
                        System.out.println("Customer " + customers.element() + " is next in the queue.");
                    }
                    case "remove" -> {
                        System.out.println("Customer " + customers.remove() + " removed from the queue.");
                    }
                    case "size" -> {
                        System.out.println("Size of queue is: " + customers.size());
                    }
                    case "position" -> {
                        System.out.println("Enter customer name");
                        name = in.nextLine();
                        int countAhead = 0;

                        for (String nextName : customers) {
                            if (!nextName.equals(name)) {
                                countAhead++;
                            } else {
                                System.out.println("The number of customers ahead of " + name + " is " + countAhead);
                                break;
                            }
                        }

                        // Check whether the customer was found.
                        if (countAhead == customers.size()) {
                            System.out.println(name + " is not in the queue.");
                        }
                    }
                    case "quit" -> {
                        System.out.println("Leaving customer queue." + "\nNumber of customers in the queue is " + customers.size());
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice: " + choice);
                } // End switch
            } catch (NoSuchElementException e) {
                System.out.println("The queue is empty.");
            } // End try-catch
        } // End while
    }

    public static void main(String[] args) {
        MaintainQueue theQueue = new MaintainQueue();
        theQueue.processCustomers();
    }
}
