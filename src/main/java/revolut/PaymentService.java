package revolut;

public class PaymentService {
    private String serviceName;

    public PaymentService(String name){
        this.serviceName = name;
    }

    public String getType() {
        return serviceName;
    }

    public boolean processPayment(Payment payment) {
        /* Do some payment processing */
        return payment.isPaymentApproved();
    }
}
