package com.swaglabs.factories;

import com.swaglabs.models.Purchase;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static io.restassured.path.json.JsonPath.from;

public class PurchaseFactory {

    private static final String PURCHASE_PATH = "data/purchase.json";

    @SneakyThrows
    public static List<Purchase> getPurchases() {
        return Arrays.asList(from(getPurchaseFile()).getObject("purchases", Purchase[].class));
    }

    @SneakyThrows
    public static Purchase getPurchaseByKey(String key) {
        return getPurchases().stream()
                .filter(a -> a.getKey()
                        .equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(String.format("purchase with key %s not found", key)));
    }

    @SneakyThrows
    private static URL getPurchaseFile() {
        String path = PURCHASE_PATH;
        URL filePath = PurchaseFactory.class.getClassLoader().getResource(path);
        if (filePath == null) {
            throw new FileNotFoundException("File not found for purchase: " + path);
        }
        return filePath;
    }
}
