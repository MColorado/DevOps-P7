Feature: Send Money

  Scenario: User sends money to a friend using the same currency
    Given Danny has a revolut EUR account
    And Jenny has a revolut EUR account
    And Danny has a starting balance of 100
    And Jenny has a starting balance of 0
    When Danny sends 50 euro to Jenny
    Then The new balance of Danny's EUR account should now be 50
    And The new balance of Jenny's EUR account should now be 50

    Scenario: User sends money to a friend using a different currency
      Given Danny has a revolut EUR account
      And Jenny has a revolut USD account
      And Danny has a starting balance of 100
      And Jenny has a starting balance of 0
      When Danny sends 50 EUR to Jenny
      Then The new balance of Danny's USD account should now be 50
      And The new balance of Jenny's USD account should now be 61