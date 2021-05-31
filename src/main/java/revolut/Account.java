package revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;
    private PaymentService defaultPaymentService;

    public Account(Currency currency, double balance, PaymentService defaultPaymentService){
        this.balance = balance;
        this.accCurrency = currency;
        this.defaultPaymentService = defaultPaymentService;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addFunds(Payment payment)
    {
        addFunds(payment, defaultPaymentService);
    }

    public void addFunds(Payment payment, PaymentService service)
    {
        if(service.processPayment(payment)) {
            this.balance += payment.getPaymentAmount();
        }
    }
}
