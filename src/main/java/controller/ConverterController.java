package com.example.converter.controller;

import com.example.converter.model.ExchangeRates;
import com.example.converter.util.APIService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Map;

public class ConverterController {
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox<String> fromCurrency;
    @FXML
    private ComboBox<String> toCurrency;
    @FXML
    private Label resultLabel;

    private Map<String, Double> rates;

    @FXML
    public void initialize() {
        ExchangeRates data = APIService.fetchRates("USD");
        if (data != null) {
            rates = data.getConversionRates();
            fromCurrency.getItems().addAll(rates.keySet());
            toCurrency.getItems().addAll(rates.keySet());
            fromCurrency.setValue("USD");
            toCurrency.setValue("EUR");
        } else {
            resultLabel.setText("Error loading exchange rates.");
        }
    }

    @FXML
    public void handleConvert() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            double from = rates.get(fromCurrency.getValue());
            double to = rates.get(toCurrency.getValue());
            double result = (amount / from) * to;

            resultLabel.setText(String.format("%.2f %s = %.2f %s",
                    amount, fromCurrency.getValue(), result, toCurrency.getValue()));
        } catch (Exception e) {
            resultLabel.setText("Error in conversion.");
        }
    }
}
