package com.caparniyazi.ds.lambda;

import java.util.Optional;

public class OptionalApp {
    public static void main(String[] args) {
        String val = "A String";
        Optional<String> opt = Optional.of(val);

        Optional<Integer> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(val);

        Integer a = 10;
        Optional<Integer> optionalInt = Optional.of(a);
        Integer intValue = optionalInt.get();
        System.out.println(intValue);

        Optional<Integer> emptyOptional = Optional.empty();
        Integer theVal = emptyOptional.isPresent() ? emptyOptional.get() : 0;
        System.out.println(theVal);

        Integer orElse = emptyOptional.orElse(0);
        System.out.println(orElse);

        Integer orElseGet = emptyOptional.orElseGet(()->0);
        System.out.println(orElseGet);

        Integer orElseThrow = emptyOptional.orElseThrow(IllegalArgumentException::new);
    }
}
