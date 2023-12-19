package com.petstore.features;

import com.petstore.conf.BaseConf;
import com.petstore.factories.GetTheUser;
import com.petstore.models.User;
import com.petstore.models.UserResponse;
import com.petstore.tasks.CreateAUser;
import net.serenitybdd.annotations.Narrative;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SerenityJUnit5Extension.class)
@Narrative(text = {"As a pet store user",
        "I want to be able to create users"})
@Tag("users")
public class CreateUser extends BaseConf {


    @Test
    @Tag("smoke")
    @Tag("create-user")
    public void should_UserBeCreated_When_RequiredDataIsSends() {

        User user = GetTheUser.withData();

        actor.describedAs("creates a user with required data")
                .attemptsTo(
                        CreateAUser.withInfo(user)
        );

        UserResponse userResponse = LastResponse.received().answeredBy(actor).body().as(UserResponse.class);

        actor.should(
                seeThat("the message response", x -> userResponse.getMessage(), equalTo(String.valueOf(user.getId())))
        );
    }
}
