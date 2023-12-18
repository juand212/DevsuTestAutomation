package com.swaglabs.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.swaglabs.ui.CartPage.CHECKOUT;
import static com.swaglabs.ui.CheckoutPage.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class AddCheckoutInformation implements Task {

    private final String firstName;
    private String lastName;
    private String postalCode;

    public AddCheckoutInformation(String firstName) {
        this.firstName = firstName;
    }

    public static AddCheckoutInformation withFirstName(String firstName) {
        return instrumented(AddCheckoutInformation.class, firstName);
    }

    public AddCheckoutInformation andLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AddCheckoutInformation andPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    @Override
    @Step("{0} add checkout information")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(CHECKOUT)
        );

        actor.should(
                seeThat(the(TITLE), isVisible())
        );

        actor.attemptsTo(
                Enter.theValue(firstName).into(FIRST_NAME),
                Enter.theValue(lastName).into(LAST_NAME),
                Enter.theValue(postalCode).into(POSTAL_CODE),
                Click.on(CONTINUE)
        );
    }
}
