package com.caparniyazi.ds.lambda;

import java.util.Optional;

public class Operations {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("Value");

        // map
        String orElse = optional.map(val -> "Replaced").orElse("Empty");
        System.out.println(orElse);

        // Filter
        Optional<String> filter = optional.filter(val -> val.equalsIgnoreCase("Value"));
        System.out.println(filter.get());

        // flatMap
        optional = Optional.empty();
        Optional<String> flatMap = optional.flatMap(val -> Optional.of("Replaced by flatMap"));
        System.out.println(flatMap);

        // ifPresent
        Optional<String> theOptional = Optional.of("Istanbul");
        theOptional.ifPresent(System.out::println);

        // ifPresentOrElse
        Optional.empty().ifPresentOrElse(System.out::println, () -> System.out.println("Value is absent."));

        // stream
        theOptional.stream().forEach(System.out::println);

        // or
        Optional.empty().or(() -> Optional.of("New value")).ifPresent(System.out::println);

        // equals
        System.out.println(theOptional.equals(Optional.of("Istanbul")));

        //  hashCode
        System.out.println(Optional.empty().hashCode());
    }
}
