package com.swaglabs.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutCompletePage {

    public static final Target SUCCESSFUL_MESSAGE = Target
            .the("successful message").locatedBy("//h2[@class='complete-header']");
}
