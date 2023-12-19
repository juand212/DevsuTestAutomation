package com.petstore.consequences;

import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.core.IsEqual.equalTo;

public class SeeThatTheResponse {

    public static Consequence successfullyWithCodeOk() {
        return seeThat("the response code", response -> LastResponse.received()
                        .answeredBy(theActorInTheSpotlight())
                        .statusCode(), equalTo(SC_OK));
    }
}
