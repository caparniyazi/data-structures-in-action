package com.caparniyazi.ds.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

public class JsonFormatterApp {
    public static void main(String[] args) throws JsonProcessingException {
        // Create a sample Person object
        Person person = new Person("Ali", LocalDate.of(1981, 1, 2));
        // Create ObjectMapper for serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // To make Jackson support Java 8 date time APIs.

        // Serialize the person object to JSON.
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println("Serialized JSON: " + json);

        // Deserialize the JSON back to a Person object.
        Person deserializedPerson = mapper.readValue(json, Person.class);
        System.out.println("Deserialized Person: " + deserializedPerson);
    }
}
