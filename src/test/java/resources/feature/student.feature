Feature: Testing different request on the student application


  Scenario: Check if the student application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code 200


  Scenario Outline: Create a new student & verify if the student is added
    When I create a new student by providing the information firstName "<firstName>" lastName "<lastName>" email "<email>" programme "<programme>" courses "<courses>"
    Then I verify that the student with "<email>" is created
    Examples:
      | firstName | lastName | email           | programme        | courses |
      | Harry     | Potter   | harry@gmail.com | Computer Science | JAVA    |


  Scenario Outline: Update student programme and verify the programme updated in database
    When I update student firstName "<firstName>" lastName "<lastName>" email "<email>" programme "<programme>" courses "<courses>"
    Then I get student data by Id and verify "<firstName>" and "<courses>" detail updated

    Examples:
      | firstName | lastName | email           | programme | courses   |
      | Bob       | Potter   | harry@gmail.com | Science   | Chemistry |


  Scenario: Delete the single student data and verify its delete from database
    When I delete single student data
    Then I verify that same student data was deleted by getting data by studentId

