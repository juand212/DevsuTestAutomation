package com.swaglabs.factories;

import com.swaglabs.models.User;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static io.restassured.path.json.JsonPath.from;

public class UserFactory {

    private static final String USERS_PATH = "data/users.json";

    @SneakyThrows
    private static List<User> getUser() {
        return Arrays.asList(from(getUsersFile()).getObject("users", User[].class));
    }

    @SneakyThrows
    public static User getUserByUsername(String userName) {
        return getUser().stream()
                .filter(a -> a.getName()
                        .equals(userName))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(String.format("user with name %s not found", userName)));
    }

    @SneakyThrows
    private static URL getUsersFile() {
        String path = USERS_PATH;
        URL filePath = UserFactory.class.getClassLoader().getResource(path);
        if (filePath == null) {
            throw new FileNotFoundException("File not found for users: " + path);
        }
        return filePath;
    }
}
