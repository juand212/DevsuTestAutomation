package com.swaglabs.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    public static final Target ITEM_PRODUCTS = Target
            .the("item products list").locatedBy("//div[@class='cart_item']");

    public static final By NAME = By.xpath(".//div[@class='inventory_item_name']");

    public static final By PRICE = By.xpath(".//div[@class='inventory_item_price']");

    public static final Target CHECKOUT = Target
            .the("checkout button").located(By.id("checkout"));
}
