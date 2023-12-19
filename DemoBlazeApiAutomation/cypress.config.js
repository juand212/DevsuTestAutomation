const { defineConfig } = require("cypress");

module.exports = defineConfig({
  projectId: "7tauio",
  reporter: 'mochawesome',
  reporterOptions: {
      reportFilename: 'index',
      reportDir: 'cypress/results',
      overwrite: false,
      html: true,
      json: true,
  },
  chromeWebSecurity: false,
  video: true,
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
});
