package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.internal.collections.Pair;
import revolut.CurrencyConverter;
import revolut.Payment;
import revolut.PaymentService;
import revolut.Person;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;
    Payment paymentRequest;
    HashMap<String, Person> people = new HashMap<>();
    HashMap<String, Pair<String, Double>> topUps = new HashMap<>();

    @Before//Before hooks run before the first step in each scenario
    public void setUp() {
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        people.put("Danny", danny);
    }

    @Given("{word} has {double} euro in his {word} Revolut account")
    public void personHasEuroInHisEuroRevolutAccount(String personName, double startingBalance, String accountType) {
        if(!people.containsKey(personName)) {
            people.put(personName, new Person(personName));
        }
        var currentPerson = people.get(personName);
        currentPerson.addAccount(accountType, startingBalance);
    }

    @Given("{word} selects {double} {word} as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(String personName, Double topUpAmount, String accountCurrency) {
        Pair<String, Double> topUp = new Pair<String,Double>(accountCurrency,topUpAmount);
        topUps.put(personName, topUp);
    }

    @Given("{word} selects his {paymentService} as his topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(String personName, PaymentService topUpSource) {
        topUpMethod = topUpSource;
    }

    @When("{word} tops up")
    public void danny_tops_up(String personName) {
        // Write code here that turns the phrase above into concrete actions
        var topUp = topUps.get(personName);
        people.get(personName).getAccount(topUp.first()).addFunds(new Payment(topUp.second()));
    }

    @Then("The new balance of {word}'s {word} account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(String personName, String currency, double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = people.get(personName).getAccount(currency).getBalance();
        //Assert
        assertEquals(expectedResult, actualResult, 0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("{word} has a starting balance of {double}")
    public void danny_has_a_starting_balance_of(String personName, double startBalance) {
        if(!people.containsKey(personName)) {
            people.put(personName, new Person(personName));
        }
        people.get(personName).setAccountBalance(startBalance);
    }

    @When("{word} now tops up by {double}")
    public void danny_now_tops_up_by(String personName, double topUpAmount) {
        people.get(personName).getAccount("EUR").addFunds(new Payment(topUpAmount));
    }

    @Then("The balance in {word}'s {word} account should be {double}")
    public void the_balance_in_his_euro_account_should_be(String personName, String currency, double endBalance) {
        // Arrange
        var expected = endBalance;
        // Act
        var actual = people.get(personName).getAccountBalance(currency);
        // Assert
        assertEquals(expected, actual, "The balance of euro account should match");
    }

    @When("Danny requests a top up by {double}")
    public void danny_requests_a_top_up_by(double amount) {
        paymentRequest = new Payment(amount);
    }

    @When("The {paymentService} provider {paymentApproval} the payment")
    public void the_debit_card_provider_rejects_the_payment(PaymentService service, boolean paymentApproval) {
        paymentRequest.setPaymentApproved(paymentApproval);
        danny.getAccount("EUR").addFunds(paymentRequest, service);
    }

    @Given("{word} has a revolut {word} account")
    public void setUpPersonHasARevolutEURAccount(String personName, String currency) {
        if(!people.containsKey(personName)) {
            people.put(personName, new Person(personName));
        }
        people.get(personName).addAccount(currency);
    }

    @When("{word} sends {int} {word} to {word}")
    public void dannySendsEuroToJenny(String originPerson, int amount, String currency, String destinationPerson) {
        var origin = people.get(originPerson);
        var destination = people.get(destinationPerson);
        origin.sendFunds(amount, currency, destination);
    }

    @When("{word} sends {int} {word} to {word}'s {word} account")
    public void dannySendsEURToJennySUSDAccount(String originPerson, int amount, String originCurrency, String destinationPerson, String destinationCurrency) {
        var origin = people.get(originPerson);
        var destination = people.get(destinationPerson);
        origin.sendFunds(amount, originCurrency, destination, destinationCurrency);
    }

    @And("exchange rate between {word} and {word} is {double}")
    public void exchangeRateBetweenEURAndUSDIs(String originCurrency, String destCurrency, double rate) {
        CurrencyConverter.addConversion(rate, originCurrency, destCurrency);
    }
}
