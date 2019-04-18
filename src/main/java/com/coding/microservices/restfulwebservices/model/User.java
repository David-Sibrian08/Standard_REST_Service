package com.coding.microservices.restfulwebservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description="All necessary details about the user")
@Entity
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @ApiModelProperty(notes = "Name should be a minimum 2 characters")
    @Size(min=2, message = "Name should be at least 2 characters")
    private String name;

    @ApiModelProperty(notes = "Birth date should always be in the past")
    @Past
    private Date birthdate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;

    protected User() {}
}
