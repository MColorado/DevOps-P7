Feature: Send Money

  Scenario: User sends money to a friend using the same currency
    Given Danny has a revolut euro account
    And Jenny has a revolut euro account
    And Danny has a starting balance of 100
    And Jenny has a starting balance of 0
    When Danny sends 50 euro to Jenny
    Then The new balance of Danny's euro account should now be 50
    And The new balance of Jenny's euro account should now be 50

    Scenario: User sends money to a friend using a different currency
      Given Danny has a revolut euro account
      And Jenny has a revolut dollar account
      And Danny has a starting balance of 100
      And Jenny has a starting balance of 0
      When Danny sends 50 euro to Jenny
      Then The new balance of Danny's euro account should now be 50
      And The new balance of Jenny's dollar account should now be 61