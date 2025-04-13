package com.caparniyazi.ds.controller;

import com.caparniyazi.ds.domain.ToDo;
import com.caparniyazi.ds.domain.ToDoBuilder;
import com.caparniyazi.ds.repository.CommonRepository;
import com.caparniyazi.ds.repository.ToDoRepositoryJPA;
import com.caparniyazi.ds.validation.ToDoValidationError;
import com.caparniyazi.ds.validation.ToDoValidationErrorBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
/*
 * RequestMapping maps requests to controller methods.
 * All methods will have the /api/v1 prefix.
 */
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ToDoController {
    // Data fields
    // private final CommonRepository<ToDo> repository;
    private final ToDoRepositoryJPA repository;

    // HTTP GET
    @GetMapping(value = "/todo",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = MediaType.ALL_VALUE
    )
    public ResponseEntity<Iterable<ToDo>> getToDos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable String id) {
        Optional<ToDo> toDo = repository.findById(id);

        return toDo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // HTTP PATCH
    @PatchMapping("/todo/{id}")
    public ResponseEntity<ToDo> setCompleted(@PathVariable String id) {
        Optional<ToDo> toDo = repository.findById(id);

        if (toDo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ToDo result = toDo.get();
        result.setCompleted(true);
        repository.save(result);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.ok().header("Location", location.toString()).build();
    }

    // HTTP POST & PUT
    /*
      Valid annotation validates the incoming data.
      If the validator finds errors, they are collected in the Errors class.
      Then you can inspect and add the necessary logic to send back an error response.
     */
    @RequestMapping(value = "/todo", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindings(errors));
        }

        ToDo result = repository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // HTTP DELETE
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable String id) {
        repository.delete(ToDoBuilder.getInstance().withId(id).build());
        return ResponseEntity.noContent().build();
    }

    // HTTP DELETE
    /*
    RequestBody annotation sends a request with a body.
    Normally, when you submit a form or a particular content, this
    class receives a JSON format "ToDo", then the HttpMessageConverter
    deserializes the JSON into a "ToDo" instance; this is done automatically
    thanks to Spring Boot and its auto-configuration because it registers
    the MappingJackson2HttpMessageConverter by default.
    <b/>
    ResponseEntity<T>. This class returns a full response,
    including HTTP headers, and the body is converted through
    HttpMessageConverters and written to the HTTP response. The
    ResponseEntity<T> class supports a fluent API, so it is easy to create
    the response.
     */
    @DeleteMapping("/todo")
    public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo todo) {
        repository.delete(todo);
        return ResponseEntity.noContent().build();
    }

    /*
    The Spring MVC automatically declares built-in
    resolvers for exceptions and adds the support to this annotation. In
    this case, the @ExceptionHandler is declared inside this controller
    class (or you can use it within a @ControllerAdvice interceptor)
    and any exception is redirected to the handleException method.
     */
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception e) {
        return new ToDoValidationError(e.getMessage());
    }
}
