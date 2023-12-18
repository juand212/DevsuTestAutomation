# Swag Labs Automation

Test project for E2E automation, in this project we will use java 11, gradle and SerenityBDD for the automation of e2e


## Use Gradle

Open a command window and run:

    ./gradlew test

## Running tests by tags

    ./gradlew clean test -Dtags='login'

## Viewing the reports

Both of the commands provided above will produce a Serenity test report in the `target/site/serenity` directory. Go take a look!

