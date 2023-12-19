package com.petstore.factories;

import com.github.javafaker.Faker;
import com.petstore.models.User;

import java.io.File;

import static io.restassured.path.json.JsonPath.from;

public class GetTheUser {

    private static final String USERS_PATH = "data/users.json";

    public static User withData() {
        File file = new File(GetTheUser.class.getClassLoader().getResource(USERS_PATH).getFile());
        User user = from(file).getObject("", User.class);
        String random = Faker.instance().random().nextInt(100, 99999).toString();
        user.setId(Integer.valueOf(random));
        user.setUsername(user.getUsername().concat(" ").concat(random));
        return user;
    }

    public static User withModifiedData(User user) {
        String random = Faker.instance().random().nextInt(10, 99).toString();
        user.setFirstName(user.getFirstName().concat(" ").concat(random));
        user.setEmail(user.getEmail().concat(random));
        return user;
    }

}
