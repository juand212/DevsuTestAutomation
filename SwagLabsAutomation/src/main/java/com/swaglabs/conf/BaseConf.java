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

    protected Actor devsuActor;
    private EnvironmentVariables environmentVariables;

    @Managed
    WebDriver browser;

    @BeforeEach
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());

        devsuActor = theActorCalled("Admin");

        devsuActor.can(BrowseTheWeb.with(browser));

        browser.manage().window().maximize();

        devsuActor.remember("baseUrl",
                EnvironmentSpecificConfiguration.from(environmentVariables)
                        .getProperty("webdriver.base.url"));
    }

}
