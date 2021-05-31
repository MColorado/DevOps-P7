package revolut;

import java.util.ArrayList;

public class CurrencyConverter {

    private static final ArrayList<Conversion> conversionList = new ArrayList<Conversion>();

    public static void addConversion(double amount, String originCurrency, String destinationCurrency) {
        conversionList.add(new Conversion(amount, originCurrency, destinationCurrency));
    }

    public static double convert(double amount, String originCurrency, String destinationCurrency) {
        for(var conversion: conversionList) {
            if(conversion.originCurrency.equalsIgnoreCase(originCurrency) && conversion.destinationCurrency.equalsIgnoreCase(destinationCurrency)) {
                return amount * conversion.exchangeRate;
            }
        }
        return amount;
    }

    private static class Conversion {
        String originCurrency;
        String destinationCurrency;
        double exchangeRate;

        public Conversion(double amount, String originCurrency, String destinationCurrency) {
            this.exchangeRate = amount;
            this.originCurrency = originCurrency;
            this.destinationCurrency = destinationCurrency;
        }
    }
}


