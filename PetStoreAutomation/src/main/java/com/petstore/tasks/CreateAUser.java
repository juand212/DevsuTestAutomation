package com.petstore.tasks;

import com.petstore.consequences.SeeThatTheResponse;
import com.petstore.models.User;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static com.petstore.enums.EndPoints.CREATE_USER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateAUser implements Task {

    private final User user;

    public CreateAUser(User user) {
        this.user = user;
    }

    public static CreateAUser withInfo(User user) {
        return instrumented(CreateAUser.class, user);
    }

    @Override
    @Step("{0} create a user with valid information")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(CREATE_USER.path())
                .with(requestSpecification ->
                        requestSpecification.contentType(ContentType.JSON)
                                .body(user))
        );

        actor.should(
                SeeThatTheResponse.successfullyWithCodeOk()
        );

    }
}
