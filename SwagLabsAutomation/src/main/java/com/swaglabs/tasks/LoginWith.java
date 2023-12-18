package com.swaglabs.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.apache.commons.lang3.StringUtils;

import static com.swaglabs.ui.LoginPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginWith implements Task {

    private final String username;
    private String password = StringUtils.EMPTY;

    public LoginWith(String username) {
        this.username = username;
    }

    public static LoginWith username(String username) {
        return instrumented(LoginWith.class, username);
    }

    public LoginWith andPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    @Step("{0} login with valid credentials")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(USERNAME),
                Enter.theValue(password).into(PASSWORD),
                Click.on(SUBMIT)
        );
    }
}
