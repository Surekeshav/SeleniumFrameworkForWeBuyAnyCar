@Regression
Feature: Search
  As a end user
  I want to search for a car valuation
  So that i can view the estimated value


  Scenario: Search for a car valuation
    Given I am Homepage
    When I read the input file "car_input.txt" and fetch vehicle registration number
    And I search for a car valuation using vehicle registration number
    Then I should be able to see respective car details and assert it from "car_output.txt" file
#    And I assert the values from "car_output.txt" file