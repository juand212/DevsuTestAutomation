package com.swaglabs.tasks;

import com.swaglabs.models.Product;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static com.swaglabs.ui.MenuPage.CART;
import static com.swaglabs.ui.ProductsPage.ITEM_PRODUCT_TO_CART;
import static com.swaglabs.ui.ProductsPage.TITLE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class AddToProducts implements Task {

    private final List<Product> products;

    public AddToProducts(List<Product> products) {
        this.products = products;
    }

    public static AddToProducts inTheCart(List<Product> products) {
        return instrumented(AddToProducts.class, products);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.should(seeThat(the(TITLE), isVisible()));

        products.forEach(
                product -> actor.attemptsTo(
                        Click.on(ITEM_PRODUCT_TO_CART.of(product.getName()))
                )
        );

        actor.attemptsTo(
                Click.on(CART)
        );
    }

}
