package com.swaglabs.conf;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class BaseConf {

    protected Actor actor;
    private EnvironmentVariables environmentVariables;

    @Managed
    WebDriver browser;

    @BeforeEach
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());

        actor = theActorCalled("Admin");

        actor.can(BrowseTheWeb.with(browser));

        browser.manage().window().maximize();

        actor.remember("baseUrl",
                EnvironmentSpecificConfiguration.from(environmentVariables)
                        .getProperty("webdriver.base.url"));
    }

}
