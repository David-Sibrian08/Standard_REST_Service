package com.coding.microservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description="All necessary details about the user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    @ApiModelProperty(notes = "Name should be a minimum 2 characters")
    @Size(min=2, message = "Name should be at least 2 characters")
    private String name;

    @ApiModelProperty(notes = "Birth date should always be in the past")
    @Past
    private Date birthdate;
}
