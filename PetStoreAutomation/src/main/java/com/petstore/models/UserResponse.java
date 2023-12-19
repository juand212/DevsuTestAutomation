package com.petstore.models;

import lombok.Data;

@Data
public class UserResponse {

    private Integer code;
    private String type;
    private String message;
}
