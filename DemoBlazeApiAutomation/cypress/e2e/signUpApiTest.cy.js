import EndPoints from "../paths/EndPoints"

describe('Sign Up tests DemoBlaze', () => {

  const api = EndPoints.api
  const format = 'application/json'

  
  beforeEach("Get valid user", function () {
    cy.fixture('user_data')
        .then(credentials => {
            cy.wrap(JSON.stringify(credentials)).as('jsonBody')
        })
  })


  it('Attempt to create an existing user', function () {
    cy.request({
      method: 'POST',
      url: api.concat(EndPoints.paths.signup),
      headers: { 'Content-Type': format},
      body: this.jsonBody
    }).then((response) => {
      expect(response.status).to.equal(200)
      expect(response.body.errorMessage).to.equal("This user already exist.")
    })
  })

  it('creating a valid user', function () {

    const randomUsername = `user_${Cypress._.random(1000)}`

    cy.request({
      method: 'POST',
      url: api.concat(EndPoints.paths.signup),
      headers: { 'Content-Type': format},
      body: {
        "username": randomUsername,
        "password": "12345"
      }
    }).then((response) => {
      expect(response.status).to.equal(200)
      expect(response.body).to.be.empty
    })
  })

})