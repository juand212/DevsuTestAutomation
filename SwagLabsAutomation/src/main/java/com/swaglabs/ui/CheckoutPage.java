package com.swaglabs.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage {

    public static final Target TITLE = Target
            .the("title checkout")
            .locatedBy("//span[contains(text(),'Checkout: Your Information')]");

    public static final Target FIRST_NAME = Target
            .the("input first name").located(By.id("first-name"));

    public static final Target LAST_NAME = Target
            .the("input last name").located(By.id("last-name"));

    public static final Target POSTAL_CODE = Target
            .the("input postal code").located(By.id("postal-code"));

    public static final Target CONTINUE = Target
            .the("button continue").located(By.id("continue"));
}
