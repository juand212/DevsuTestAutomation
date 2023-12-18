package com.swaglabs.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final Target USERNAME = Target
            .the("username")
            .located(By.id("user-name"));

    public static final Target PASSWORD = Target
            .the("password")
            .located(By.id("password"));

    public static final Target SUBMIT = Target
            .the("login button")
            .located(By.id("login-button"));
}
