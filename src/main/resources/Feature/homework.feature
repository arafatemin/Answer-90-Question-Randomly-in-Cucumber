
Feature: test uyghuriye page
  Scenario: test mock test result on uyghuriye website
    Given user is launching question page
    When user clicks second MockTest button on home page
    And user answers questions randomly
    When user submits exam questions
    Then verifies if answers matches on result page
    And users verifies if  correct answers are green otherwise red
    Then user verifies all questions are on the result page