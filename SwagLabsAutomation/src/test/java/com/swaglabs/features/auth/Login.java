package com.swaglabs.features.auth;


import com.swaglabs.conf.BaseConf;
import com.swaglabs.models.User;
import com.swaglabs.tasks.LoginWith;
import com.swaglabs.tasks.NavigateTo;
import net.serenitybdd.annotations.Narrative;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.swaglabs.enums.Users.ADMIN;
import static com.swaglabs.factories.UserFactory.getUserByUsername;
import static com.swaglabs.ui.MenuPage.CART;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

@ExtendWith(SerenityJUnit5Extension.class)
@Narrative(text = {"As a user of swag labs",
        "I want to be able to login",
        "To be able to manage my products"})
@Tag("login")
public class Login extends BaseConf {

    User user;

    @BeforeEach
    public void navigate() {
        devsuActor.attemptsTo(
                NavigateTo.loginPage());
    }

    @Test
    @Tag("data")
    public void should_UserLoginSuccessfully_When_HeSendsValidCredentials() {
        user = getUserByUsername(ADMIN.getUserName());

        devsuActor.describedAs("login with valid credentials");
        devsuActor.attemptsTo(
                LoginWith.username(user.getUsername())
                        .andPassword(user.getPassword())
        );
        devsuActor.should(
                seeThat(the(CART), isVisible())
        );
    }
}
