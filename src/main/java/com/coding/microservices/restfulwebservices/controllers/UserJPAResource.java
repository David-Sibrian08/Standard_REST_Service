package com.coding.microservices.restfulwebservices.controllers;

import com.coding.microservices.restfulwebservices.exceptions.UserNotFoundException;
import com.coding.microservices.restfulwebservices.model.User;
import com.coding.microservices.restfulwebservices.repository.UserRepository;
import com.jfilter.filter.FieldFilterSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;


    @FieldFilterSetting(fields = {"birthdate"})
    @GetMapping(value = "/jpa/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Resource<User> getUser(@PathVariable int id) {

        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id- " + id);
        }

        Resource<User> resource = new Resource<>(user.get());
        ControllerLinkBuilder linkTo =ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping(value = "/jpa/users")
    public void createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

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

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
