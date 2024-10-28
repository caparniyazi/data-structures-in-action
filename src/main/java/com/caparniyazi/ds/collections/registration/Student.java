package com.caparniyazi.ds.collections.registration;

public record Student(String firstName, String lastName) {
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            return firstName.equals(((Student) obj).firstName) &&
                    lastName.equals(((Student) obj).lastName);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return firstName + ", " + lastName;
    }
}
