Feature: 1Authorization

  Scenario: 1Authorization
    Given i am on yandex
    When the authorization page is open
    And login entered
    And password entered
    Then i am see the yandex home page