package com.petstore.features;

import com.petstore.conf.BaseConf;
import com.petstore.factories.GetTheUser;
import com.petstore.models.User;
import com.petstore.models.UserResponse;
import com.petstore.tasks.CreateAUser;
import com.petstore.tasks.DeleteAUser;
import net.serenitybdd.annotations.Narrative;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SerenityJUnit5Extension.class)
@Narrative(text = {"As a pet store user",
        "I want to be able to delete a user"})
@Tag("users")
public class DeleteUser extends BaseConf {

    User user;

    @BeforeEach
    public void background() {
        user = GetTheUser.withData();
        actor.wasAbleTo(
                CreateAUser.withInfo(user)
        );
    }


    @Test
    @Tag("smoke")
    @Tag("delete-user")
    public void should_DeleteAUser_When_UserIsSent() {

        actor.describedAs("delete a user by username")
                .attemptsTo(
                        DeleteAUser.withUsername(user.getUsername())
                );

        UserResponse userResponse = LastResponse.received().answeredBy(actor).body().as(UserResponse.class);

        actor.should(
                seeThat("the username", x -> userResponse.getMessage(), equalTo(user.getUsername()))
        );
    }
}
