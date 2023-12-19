package com.petstore.tasks;

import com.petstore.consequences.SeeThatTheResponse;
import com.petstore.models.User;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import static com.petstore.enums.EndPoints.UPDATE_USER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.Matchers.equalTo;

public class UpdateAUser implements Task {

    private final User user;

    public UpdateAUser(User user) {
        this.user = user;
    }

    public static UpdateAUser withInfo(User user) {
        return instrumented(UpdateAUser.class, user);
    }

    @Override
    @Step("{0} update a user with valid information")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Put.to(UPDATE_USER.path())
                .with(requestSpecification ->
                        requestSpecification.contentType(ContentType.JSON)
                                .body(user).pathParam("username", user.getUsername()))
        );

        actor.should(
                SeeThatTheResponse.successfullyWithCodeOk()
        );

        actor.should(
                seeThat("the message response", x -> LastResponse.received().answeredBy(actor).body().jsonPath().getString("message"),
                        equalTo(String.valueOf(user.getId())))
        );

    }
}
