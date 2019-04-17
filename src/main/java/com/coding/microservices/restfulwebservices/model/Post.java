package com.coding.microservices.restfulwebservices.model;

import lombok.*;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)      //will not retrieve user details unless called
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
