# Demo Blaze Api Automation

Test project for API automation, in this project we will use cypress


## Requirements

we must have node.js installed and a development id, we could use Visual Studio Code.


## Running tests by tags

we can run the tests from the cypres wizard or from the command console. "npx cypress run" will execute all project scenarios.

    npm run cypress:open 
    npx cypress run


## Viewing the reports and videos

the route of the reports and videos of the tests can be found at the `cypress/results` and  `cypress/videos` directory. Go take a look!

## Notes

If for some reason the dependencies do not load, run the command 

    npm install cypress --save-dev
    npm install mochawesome --save-dev

