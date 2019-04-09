package com.coding.microservices.restfulwebservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
public class ExceptionResponse {

    @Getter private Date timestamp;
    @Getter private String message;
    @Getter private String details;



}
