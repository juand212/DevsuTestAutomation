package com.petstore.tasks;

import com.petstore.consequences.SeeThatTheResponse;
import com.petstore.enums.Memory;
import com.petstore.models.User;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import static com.petstore.enums.EndPoints.GET_USER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetAUser implements Task {

    private final String username;

    public GetAUser(String username) {
        this.username = username;
    }

    public static GetAUser withUsername(String username) {
        return instrumented(GetAUser.class, username);
    }

    @Override
    @Step("{0} get a user by username #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(GET_USER.path())
                .with(requestSpecification ->
                        requestSpecification.contentType(ContentType.JSON)
                                .pathParam("username", username))
        );

        actor.should(
                SeeThatTheResponse.successfullyWithCodeOk()
        );

        actor.remember(Memory.USER.toString(),
                LastResponse.received().answeredBy(actor).body().as(User.class)
        );

    }
}
