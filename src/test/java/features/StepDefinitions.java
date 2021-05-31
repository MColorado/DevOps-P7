package features;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.Payment;
import revolut.PaymentService;
import revolut.Person;

import static org.testng.Assert.assertEquals;

public class StepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        danny.setAccountBalance(startingBalance);
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    @Given("Danny selects his {paymentService} as his topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(new Payment(topUpAmount));
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("Danny has a starting balance of {double}")
    public void danny_has_a_starting_balance_of(double startBalance) {
        danny.setAccountBalance(startBalance);
    }

    @When("Danny now tops up by {double}")
    public void danny_now_tops_up_by(double topUpAmount) {
        danny.getAccount("EUR").addFunds(new Payment(topUpAmount));
    }

    @Then("The balance in his euro account should be {double}")
    public void the_balance_in_his_euro_account_should_be(double endBalance) {
        // Arrange
        var expected = endBalance;
        // Act
        var actual = danny.getAccountBalance("EUR");
        // Assert
        assertEquals(expected, actual, "The balance of euro account should match");
    }

    @When("The {paymentService} provider rejects the payment")
    public void the_debit_card_provider_rejects_the_payment(PaymentService service) {

    }

    @When("The {paymentService} provider accepts the payment")
    public void the_debit_card_provider_accepts_the_payment() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
