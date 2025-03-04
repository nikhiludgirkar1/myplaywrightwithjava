@addemployee
Feature: This feature deals with all test cases of add employee feature

  Background: Perform login
    Given User is on orangehrm portal
    When User performs login using valid credentials with username "admin" and password "Trigent@2022"
    Then welcome page is displayed for "Admin"

    @addemployee-test
    Scenario: Add new employee test
      When add employee button
#      And the user opens a new browser tab with the copied link
      When user gets response for users
      And user creates another user with name "Ayesha Begum" and email "ayesha@gmail.com"
