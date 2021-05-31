package revolut;

import java.util.Currency;
import java.util.HashMap;

public class Person {

    private String name;
    //Accounts currency & balance
    // EUR 30
    // USD 50
    // STG 30
    private HashMap<String, Account> userAccounts = new HashMap<String, Account>();

    public Person(String name){
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        Currency accCurrency = Currency.getInstance("EUR");
        Account euroAccount = new Account(accCurrency, 0, new PaymentService("EUR"));
        userAccounts.put("EUR", euroAccount);
    }

    public void setAccountBalance(double startingBalance) {
        userAccounts.get("EUR").setBalance(startingBalance);
    }

    public double getAccountBalance(String eur) {
        return userAccounts.get("EUR").getBalance();
    }

    public Account getAccount(String account) {
        return userAccounts.get(account);
    }

    public void addAccount(String accountType) {
        addAccount(accountType, 0);
    }

    public void addAccount(String accountType, double startingBalance) {
        Currency accCurrency = Currency.getInstance(accountType);
        Account euroAccount = new Account(accCurrency, startingBalance, new PaymentService(accountType));
        userAccounts.put(accountType, euroAccount);
    }

    public void sendFunds(double amount, String currency, Person destination) {
        sendFunds(amount, currency, destination, currency);
    }

    public void sendFunds(double amount, String originCurrency, Person destination, String destinationCurrency) {
        if(amount > 0) {
            var account = userAccounts.get(originCurrency);
            if(account.getBalance() >= amount) {
                account.addFunds(new Payment(-amount));
            }
            var destinationAccount = destination.getAccount(destinationCurrency);
            destinationAccount.addFunds(new Payment(CurrencyConverter.convert(amount, originCurrency, destinationCurrency)));
        }
    }
}
