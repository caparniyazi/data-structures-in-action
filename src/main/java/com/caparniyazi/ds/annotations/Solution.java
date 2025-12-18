package com.caparniyazi.ds.annotations;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int testCases = Integer.parseInt(scanner.nextLine());

            while (testCases-- > 0) {
                String role = scanner.next();
                int spend = scanner.nextInt();

                Class annotatedClass = FamilyMember.class;
                Method[] methods = annotatedClass.getMethods();

                for (Method method : methods) {
                    if (method.isAnnotationPresent(FamilyBudget.class)) {
                        FamilyBudget familyBudget = method.getAnnotation(FamilyBudget.class);
                        String userRole = familyBudget.userRole();
                        int budgetLimit = familyBudget.budgetLimit();

                        if (userRole.equals(role)) {
                            if (spend <= budgetLimit) {
                                method.invoke(FamilyMember.class.newInstance(), budgetLimit, spend);
                            } else {
                                System.out.println("Budget limit over");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
