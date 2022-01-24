Feature: Application users



  Scenario: Could not show all users without token
    When I Get it to the /users endpoint
    Then I receive a 401 status code

  Scenario: Could not show one user without token
    When I Get it to the /users/{id} endpoint
    Then I receive a 401 status code

  Scenario: Could not add a user without token
    Given I have an user payload
    When I POST it to the /users endpoint
    Then I receive a 401 status code

  Scenario: Could not update a user without token
    Given I have an userAdmin payload
    When I Update it by the id to the /users endpoint
    Then I receive a 401 status code

  Scenario: Could not delete a user without token
    When I Delete it by the id to the /users endpoint
    Then I receive a 401 status code

  Scenario: Authenticate with user
    Given I have an userAdmin payload
    When I Post it to the /authenticate endpoint
    Then I receive a 200 status code
    Given I have a token

  Scenario: Show all users with token
    Given I have an userAdmin payload
    When I Post it to the /authenticate endpoint
    Given I have a token
    When I Get it to the /users endpoint
    Then I receive a 200 status code

  Scenario: Show one user with token
    Given I have an userAdmin payload
    When I Post it to the /authenticate endpoint
    Given I have a token
    When I Get it to the /users/{id} endpoint
    Then I receive a 200 status code

  Scenario: Post Put Delete a user with token
    Given I have an userAdmin payload
    When I Post it to the /authenticate endpoint
    Given I have a token
    Given I have an user payload
    When I POST it to the /users endpoint
    Then I receive a 201 status code
    When I Update it by the id to the /users endpoint
    Then I receive a 200 status code
    When I Delete it by the id to the /users endpoint
    Then I receive a 200 status code

