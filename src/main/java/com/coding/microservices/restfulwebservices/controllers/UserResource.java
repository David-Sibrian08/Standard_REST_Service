package com.coding.microservices.restfulwebservices.controllers;

import com.coding.microservices.restfulwebservices.DAO.UserDAOService;
import com.coding.microservices.restfulwebservices.exceptions.UserNotFoundException;
import com.coding.microservices.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserResource {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    UserDAOService userDAOService;


    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userDAOService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {

        User user = userDAOService.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id- " + id);
        }

        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    @PostMapping(value = "/users")
    public void createUser(@Valid @RequestBody User user) {
        User savedUser = userDAOService.save(user);

        //Create a URI for the location of the resource that was created
        //Append the ID of the created user to form a more detailed URI
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        //Build the full URI with the location
        //We will also return a 201 Created status
        ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {

        User user =  userDAOService.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException("id- " + id);
        }
    }

    @GetMapping(value="/i18n", produces = "text/plain")
    public String greetUser(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
