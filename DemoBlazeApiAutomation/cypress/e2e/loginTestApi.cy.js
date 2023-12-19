import EndPoints from "../paths/EndPoints"

describe('Login tests DemoBlaze', () => {

  const api = EndPoints.api
  const format = 'application/json'
  
  beforeEach("Get credentials", function () {
    cy.fixture('user_data')
        .then(credentials => {
            cy.wrap(JSON.stringify(credentials)).as('jsonBody')
        })
  })


  it('login with valid credentials', function () {
    cy.request({
      method: 'POST',
      url: api.concat(EndPoints.paths.login),
      headers: { 'Content-Type': format},
      body: this.jsonBody
    }).then((response) => {
      expect(response.status).to.equal(200)
      expect(response.body).to.contain("Auth_token:")
    })
  })

  it('login with non-existent user', function () {
    cy.request({
      method: 'POST',
      url: api.concat(EndPoints.paths.login),
      headers: { 'Content-Type': format},
      body: {
        "username": "fake12315165",
        "password": "YWRtaW4="
      }
    }).then((response) => {
      expect(response.status).to.equal(200)
      expect(response.body.errorMessage).to.equal("User does not exist.")
    })
  })

  it('login with password invalid', function () {
    cy.request({
      method: 'POST',
      url: api.concat(EndPoints.paths.login),
      headers: { 'Content-Type': format},
      body: {
        "username": "admin",
        "password": "YWRtaW4=23423"
      }
    }).then((response) => {
      expect(response.status).to.equal(200)
      expect(response.body.errorMessage).to.equal("Wrong password.")
    })
  })

})