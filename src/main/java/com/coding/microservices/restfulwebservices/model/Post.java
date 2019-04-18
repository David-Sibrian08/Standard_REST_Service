package com.coding.microservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Proxy(lazy = false)
@Entity
public class Post {

    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)      //will not retrieve user details unless called
    @JsonIgnore
    @Getter @Setter
    private User user;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
