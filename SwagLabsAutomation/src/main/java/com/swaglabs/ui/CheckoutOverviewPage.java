package com.swaglabs.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutOverviewPage {

    public static final Target TITLE = Target
            .the("title overview")
            .locatedBy("//span[contains(text(),'Checkout: Overview')]");

    public static final Target VALUE = Target
            .the("value products")
            .locatedBy("//div[@class='summary_subtotal_label']");

    public static final Target TOTAL = Target
            .the("total products")
            .locatedBy("//div[@class='summary_info_label summary_total_label']");

    public static final Target FINISH = Target
            .the("finish button")
            .located(By.id("finish"));

}
