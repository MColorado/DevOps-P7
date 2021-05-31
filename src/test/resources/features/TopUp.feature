Feature: TopUp Account
  This feature describes various scenarios for users adding funds to their revolut account(s)

  #As a user, I can topup my Revolut account using my debit card

  Scenario: Add money to Revolut account using debit card
    Given Danny has 10 euro in his EUR Revolut account
    And Danny selects 100 EUR as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    When Danny tops up
    Then The new balance of Danny's EUR account should now be 110


  Scenario: Add money to Revolut account using bank account
    Given Danny has 20 euro in his EUR Revolut account
    And Danny selects 230 EUR as the topUp amount
    And  Danny selects his BankAccount as his topUp method
    When Danny tops up
    Then The new balance of Danny's EUR account should now be 250

  Scenario Outline: Add various amounts to Revolut account
    Given Danny has a starting balance of <startBalance>
    And Danny selects his DebitCard as his topUp method
    When Danny now tops up by <topUpAmount>
    Then The balance in Danny's EUR account should be <newBalance>
    Examples:
      | startBalance| topUpAmount | newBalance  |
      | 0           | 100         | 100         |
      | 14          | 20          | 34          |
      | 23          | 30          | 53          |

  Rule: The account balance shouldn't change if the topUp payment request is rejected by the payment service

    Scenario: Payment service rejects the request
      Given Danny has a starting balance of 20
      And Danny selects his DebitCard as his topUp method
      When Danny requests a top up by 100
      And The DebitCard provider rejects the payment
      Then The balance in Danny's EUR account should be 20

    Scenario: Payment service accepts the request
      Given Danny has a starting balance of 20
      And Danny selects his DebitCard as his topUp method
      When Danny requests a top up by 100
      And The DebitCard provider accepts the payment
      Then The balance in Danny's EUR account should be 120