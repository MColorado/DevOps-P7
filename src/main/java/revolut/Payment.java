package revolut;

public class Payment {
    private final double paymentAmount;
    private boolean isPaymentApproved = true;

    public Payment(double amount) {
        paymentAmount = amount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public boolean isPaymentApproved() {
        return isPaymentApproved;
    }

    public void setPaymentApproved(boolean paymentApproved) {
        isPaymentApproved = paymentApproved;
    }
}
