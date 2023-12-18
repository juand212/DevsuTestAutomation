package com.swaglabs.features.purchase;

import com.swaglabs.conf.BaseConf;
import com.swaglabs.models.Purchase;
import com.swaglabs.models.User;
import com.swaglabs.tasks.*;
import net.serenitybdd.annotations.Narrative;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.swaglabs.enums.Users.ADMIN;
import static com.swaglabs.factories.PurchaseFactory.getPurchaseByKey;
import static com.swaglabs.factories.UserFactory.getUserByUsername;
import static com.swaglabs.ui.CheckoutCompletePage.SUCCESSFUL_MESSAGE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(SerenityJUnit5Extension.class)
@Narrative(text = {"As a user of swag labs",
        "I want to be able add products to cart",
        "To be able to purchase products"})
@Tag("purchase")
public class PurchaseProducts extends BaseConf {

    @BeforeEach
    public void background() {
        User user = getUserByUsername(ADMIN.getUserName());
        actor.attemptsTo(
                NavigateTo.loginPage(),
                LoginWith
                        .username(user.getUsername())
                        .andPassword(user.getPassword())
        );
    }

    @Test
    @Tag("smoke")
    public void should_ThePurchaseBeCreated_When_SendsProductsToTheShoppingCart() {

        Purchase purchase = getPurchaseByKey("with-two-products");
        //Purchase purchase = getPurchaseByKey("with-three-products");

        actor.describedAs("create a purchase of products in the cart");

        actor.attemptsTo(
                AddToProducts
                        .inTheCart(purchase.getProducts()),
                SeeThatTheProducts
                        .areExpected(purchase.getProducts()),
                AddCheckoutInformation
                        .withFirstName(purchase.getFirstName())
                        .andLastName(purchase.getLastName())
                        .andPostalCode(purchase.getPostalCode()),
                SeeThatTheCheckoutInformation
                        .areExpected(purchase)
        );
        actor.should(
                seeThat("", a -> SUCCESSFUL_MESSAGE.resolveFor(actor).getText(),
                        equalTo("Thank you for your order!"))
        );
    }

}
