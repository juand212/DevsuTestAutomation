package com.swaglabs.enums;

public enum Users {

    ADMIN("Admin")
    ;
    private final String name;

    Users(String name) {
        this.name = name;
    }

    public String getUserName() {
        return name;
    }
}
