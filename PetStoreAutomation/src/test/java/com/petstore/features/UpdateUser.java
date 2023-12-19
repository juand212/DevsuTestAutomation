package com.petstore.features;

import com.petstore.conf.BaseConf;
import com.petstore.enums.Memory;
import com.petstore.factories.GetTheUser;
import com.petstore.models.User;
import com.petstore.tasks.CreateAUser;
import com.petstore.tasks.GetAUser;
import com.petstore.tasks.UpdateAUser;
import net.serenitybdd.annotations.Narrative;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SerenityJUnit5Extension.class)
@Narrative(text = {"As a pet store user",
        "I want to be able to update a user"})
@Tag("users")
public class UpdateUser extends BaseConf {

    User user;

    @BeforeEach
    public void background() {
        user = GetTheUser.withData();
        actor.wasAbleTo(
                CreateAUser.withInfo(user)
        );
    }


    @Test
    @Tag("update-user")
    public void should_UpdateTheUser_When_ModifiesUserData() {

        User userUpdate = GetTheUser.withModifiedData(user);

        actor.describedAs("update a user and get by username")
                .attemptsTo(
                        UpdateAUser.withInfo(userUpdate),
                        GetAUser.withUsername(user.getUsername())
                );

        User userResponse = actor.recall(Memory.USER.toString());

        actor.should(
                seeThat("the user information", x -> userResponse, equalTo(userUpdate))
        );
    }
}
