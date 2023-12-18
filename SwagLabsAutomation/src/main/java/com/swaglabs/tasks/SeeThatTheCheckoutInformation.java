package com.swaglabs.tasks;

import com.swaglabs.models.Purchase;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;


import static com.swaglabs.ui.CheckoutOverviewPage.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.CoreMatchers.equalTo;

public class SeeThatTheCheckoutInformation implements Task {

    private final Purchase expectedPurchase;

    public SeeThatTheCheckoutInformation(Purchase expectedPurchase) {
        this.expectedPurchase = expectedPurchase;
    }

    public static SeeThatTheCheckoutInformation areExpected(Purchase expectedPurchase) {
        return instrumented(SeeThatTheCheckoutInformation.class, expectedPurchase);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.should(
                seeThat(the(TITLE), isVisible())
        );

        actor.attemptsTo(
                SeeThatTheProducts.areExpected(expectedPurchase.getProducts())
        );

        actor.should(
                seeThat("value products", a -> VALUE.resolveFor(actor).getText().split(":")[1].trim(),
                        equalTo(expectedPurchase.getValue())
                ),
                seeThat("total products", a -> TOTAL.resolveFor(actor).getText().split(":")[1].trim(),
                        equalTo(expectedPurchase.getTotal()))
        );

        actor.attemptsTo(Click.on(FINISH));
    }
}
