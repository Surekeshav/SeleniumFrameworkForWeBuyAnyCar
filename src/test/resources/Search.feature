@Regression
Feature: Search
  As a end user
  I want to search for a car valuation
  So that i can view the estimated value


  Scenario: Search for a car valuation
    Given I am Homepage
    When I read the input file "car_input.txt" and fetch vehicle registration number
    Then I search and assert the car details using "car_output.txt" file
