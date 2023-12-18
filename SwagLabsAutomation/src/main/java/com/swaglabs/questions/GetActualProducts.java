package com.swaglabs.questions;

import com.swaglabs.models.Product;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.stream.Collectors;

import static com.swaglabs.ui.CartPage.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class GetActualProducts {

    public static Question<List<Product>> information() {
        return actor -> ITEM_PRODUCTS
                .resolveAllFor(theActorInTheSpotlight())
                .stream()
                .map(productItem -> {
                    Product product = new Product();
                    product.setName(productItem.findElement(NAME).getText());
                    product.setPrice(productItem.findElement(PRICE).getText());
                    return product;
                })
                .collect(Collectors.toList());
    }
}
