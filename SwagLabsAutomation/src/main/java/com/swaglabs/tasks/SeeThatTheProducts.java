package com.swaglabs.tasks;

import com.swaglabs.models.Product;
import com.swaglabs.questions.GetActualProducts;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static com.swaglabs.ui.CartPage.CHECKOUT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.Matchers.equalTo;

public class SeeThatTheProducts implements Task {

    private final List<Product> expectedProducts;

    public SeeThatTheProducts(List<Product> expectedProducts) {
        this.expectedProducts = expectedProducts;
    }

    public static SeeThatTheProducts areExpected(List<Product> expectedProducts) {
        return instrumented(SeeThatTheProducts.class, expectedProducts);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        List<Product> actualProducts = GetActualProducts.information().answeredBy(actor);

        actor.should(
                seeThat("the aggregate products",
                        a -> actualProducts, equalTo(expectedProducts))
        );

    }
}
