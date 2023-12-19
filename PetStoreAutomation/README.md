# Pet Store Automation

Test project for API automation, in this project we will use java 11, gradle and SerenityRest for the automation of APIS


## Use Gradle

Open a terminal and run:

    ./gradlew test
    gradlew test

you could use gradlew or ./gradlew.

## Running tests by tags

    ./gradlew tags -DincludeTags='users'
    ./gradlew tags -DincludeTags='smoke'

## include execution report

Open a terminal and run:

    ./gradlew aggregate

## Viewing the reports

Both of the commands provided above will produce a Serenity test report in the `target/site/serenity` directory. Go take a look!

