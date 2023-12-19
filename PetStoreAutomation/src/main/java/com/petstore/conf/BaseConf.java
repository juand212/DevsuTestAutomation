package com.petstore.conf;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.DecoderConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public abstract class BaseConf {

    private EnvironmentVariables environmentVariables;
    protected Actor actor;


    @BeforeEach
    public void setBaseConfiguration() {

        OnStage.setTheStage(new Cast());
        actor = theActorCalled("Admin");

        SerenityRest.config().decoderConfig(DecoderConfig
                .decoderConfig().defaultContentCharset("UTF-8"));
        actor.can(CallAnApi.at(
                EnvironmentSpecificConfiguration.from(environmentVariables)
                        .getProperty("service.base.url")
        ));
        SerenityRest.setDefaultRequestSpecification(defaultRequestSpecification());
    }

    private RequestSpecification defaultRequestSpecification() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());
        return new RequestSpecBuilder().addFilters(filters).build();
    }
}