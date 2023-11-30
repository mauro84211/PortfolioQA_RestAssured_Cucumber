Feature: Users

  Scenario Outline: Create an user
    Given an user provide information <username>
    When a user send the information
    Then the status code is <statusCode>
    And the response includes correct type
    Examples:
      | username    | statusCode |
      | "Maurice31" | 200        |

  Scenario Outline: Create users with array
   Given a user provide users information
   When the users information is sent
   Then the status code is <statusCode>
   And the response includes correct <message>
   Examples:
     | statusCode | message |
     | 200        | "ok"    |

  Scenario Outline: Get user details by username
    Given a user exists with an <username>
    When a user retrieves the user details by <username>
    Then the status code is <statusCode>
    And the response includes correct user data
    Examples:
      | username    | statusCode |
      | "Maurice89" | 200        |