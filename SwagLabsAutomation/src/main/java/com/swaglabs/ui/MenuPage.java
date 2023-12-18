package com.swaglabs.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class MenuPage {

    public static final Target CART = Target
            .the("cart button")
            .located(By.className("shopping_cart_link"));

}
