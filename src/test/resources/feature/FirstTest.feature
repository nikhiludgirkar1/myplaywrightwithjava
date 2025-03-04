@first
Feature: This is first test

  @firsttest
  Scenario: Searching with google
    When user navigates to google.com
    And user clicks on images
    Then images page is loaded
