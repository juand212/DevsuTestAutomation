package com.petstore.tasks;

import com.petstore.consequences.SeeThatTheResponse;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static com.petstore.enums.EndPoints.DELETE_USER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteAUser implements Task {

    private final String username;

    public DeleteAUser(String username) {
        this.username = username;
    }

    public static DeleteAUser withUsername(String username) {
        return instrumented(DeleteAUser.class, username);
    }

    @Override
    @Step("{0} get a user by username #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Delete.from(DELETE_USER.path())
                .with(requestSpecification ->
                        requestSpecification.contentType(ContentType.JSON)
                                .pathParam("username", username))
        );

        actor.should(
                SeeThatTheResponse.successfullyWithCodeOk()
        );

    }
}
