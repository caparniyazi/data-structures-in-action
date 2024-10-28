package com.caparniyazi.ds.collections.registration;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * The class to manage a list of students waiting to register for a course.
 * Operations include adding a new student at the end of the list, adding a new
 * student at the beginning of the list, removing the student from the beginning of the list,
 * and removing a student by name.
 */
public class CourseRegistration {
    public static void main(String[] args) {
        LinkedList<Student> students = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("STUDENTS LINE");
            System.out.printf("%-20s%-20s\n", "First Name", "Last Name");
            System.out.printf("%-20s%-20s\n", "----------", "---------");

            for (Student student : students) {
                System.out.printf("%-20s%-20s\n", student.firstName(), student.lastName());
            }

            System.out.println();
            System.out.println("Options: ");
            System.out.println("1. Add a new student at the end of the list.");
            System.out.println("2. Add a new student at the beginning of the list.");
            System.out.println("3. Remove student from the beginning of the list.");
            System.out.println("4. Remove student by name.");
            System.out.println("0. Quit");
            System.out.print("Option: ");
            String option = scanner.nextLine();
            System.out.println();

            if (option.equals("0")) {
                System.exit(0);
            }

            if (!option.equals("1") && !option.equals("2") && !option.equals("4")) {
                if (option.equals("3")) {
                    if (students.isEmpty()) {
                        System.out.println("Error: There is no students.");
                    } else {
                        Student student = students.removeFirst();
                        System.out.println("Student '" + student + "' removed.");
                    }
                } else {
                    System.out.println("Error: Invalid option.");
                }
            } else {
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine().trim();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine().trim();

                if (!firstName.isEmpty() && !lastName.isEmpty()) {
                    Student student = new Student(firstName, lastName);

                    if (!option.equals("1") && !option.equals("2")) {
                        if (!students.remove(student)) {
                            System.out.println("Error: Student does not exist.");
                        } else {
                            System.out.println("Ok: Student removed.");
                        }
                    } else if (students.contains(student)) {
                        System.out.println("Error: Student currently exists.");
                    } else {
                        if (option.equals("1")) {
                            students.addLast(student);
                        } else {
                            students.addFirst(student);
                        }
                        System.out.println("Ok: Student added.");
                    }
                } else {
                    System.out.println("Error: All fields are required.");
                }
            }
        }
    }
}

