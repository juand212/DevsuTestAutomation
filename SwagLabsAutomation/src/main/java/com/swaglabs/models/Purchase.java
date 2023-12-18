package com.swaglabs.models;

import lombok.Data;

import java.util.List;

@Data
public class Purchase {

    private String key;
    private String firstName;
    private String lastName;
    private String postalCode;
    private List<Product> products;
    private String value;
    private String total;
}
