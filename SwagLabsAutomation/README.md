# Swag Labs Automation

Test project for E2E automation, in this project we will use java 11, gradle and SerenityBDD for the automation of e2e


## Use Gradle

Open a terminal and run:

    ./gradlew test

## Running tests by tags

    ./gradlew tags -DincludeTags='login'
    ./gradlew tags -DincludeTags='purchase'
    ./gradlew tags -DincludeTags='smoke'

## include execution report

Open a terminal and run:

    ./gradlew aggregate

## Viewing the reports

Both of the commands provided above will produce a Serenity test report in the `target/site/serenity` directory. Go take a look!

