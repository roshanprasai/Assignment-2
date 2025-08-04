import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Map;

public class Controller {
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
            resultLabel.setText("Failed to fetch data.");
        }
    }

    @FXML
    public void handleConvert() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            double from = rates.get(fromCurrency.getValue());
            double to = rates.get(toCurrency.getValue());
            double converted = (amount / from) * to;
            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency.getValue(), converted, toCurrency.getValue()));
        } catch (Exception e) {
            resultLabel.setText("Invalid input or error.");
        }
    }
}
