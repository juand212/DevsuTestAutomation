package com.petstore.enums;

public enum EndPoints {

    CREATE_USER("user"),
    GET_USER("user/{username}"),
    UPDATE_USER("user/{username}"),
    DELETE_USER("user/{username}")
    ;

    private final String path;

    EndPoints(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
