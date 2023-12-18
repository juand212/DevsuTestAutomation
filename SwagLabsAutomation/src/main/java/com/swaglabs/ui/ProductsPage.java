package com.swaglabs.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductsPage {

    public static final Target TITLE = Target
            .the("title page")
            .locatedBy("//span[contains(text(),'Products')]");

    public static final Target ITEM_PRODUCT = Target
            .the("item product")
            .locatedBy("//div[contains(text(),'{0}')]");

    public static final Target ITEM_PRODUCT_TO_CART = Target
            .the("item product")
            .locatedBy("//div[@class='inventory_item_name ' and contains(text(),'{0}')]//parent::a//parent::div//following::div[@class='pricebar']/button");
}
