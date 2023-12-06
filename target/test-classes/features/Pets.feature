Feature: Pets
  Scenario Outline: Add new Pet profile
    Given a Manager add new <category> pet with <name>
    When a new pet is added
    Then the status code should be <statusCode>
    And the response includes correct pet data

    Examples:
      | category | name   | statusCode |
      | "Cat"    | "Lolo" | 200        |

  Scenario Outline: Get a pet by Id
    Given a Pet exists with <id> category <category> and name <name>
    When a user retrieves the Pet details by <id>
    Then the status code should be <statusCode>
    And the response includes correct pet data

    Examples:
      | id  | category | name       | statusCode |
      | 056 | "Dog"    | "Firulais" | 200        |

  Scenario Outline: Update a pet by Id
    Given a Pet exists with <id> category <category> and name <name>
    When a user updates <second_name> and <status>
    Then the status code should be <statusCode>
    And the new pet's data is correct

    Examples:
      | id  | category | name       | statusCode | second_name       | status    |
      | 056 | "Dog"    | "Firulais" | 200        | "Firulais Second" | "pending" |

  Scenario Outline: Delete a pet by Id
    Given a Pet exists with <id> category <category> and name <name>
    When a user delete the pet
    Then the status code should be <statusCode>
    And the response contains <id> deleted

    Examples:
      | id  | category | name       | statusCode |
      | 056 | "Dog"    | "Firulais" | 200        |