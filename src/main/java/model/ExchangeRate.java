package model;

import java.util.Map;

public class ExchangeRate {
    private String result;
    private String base_code;
    private Map<String, Double> conversion_rates;

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public String getResult() {
        return result;
    }

    public String getBaseCode() {
        return base_code;
    }
}
