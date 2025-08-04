package util;


import com.google.gson.Gson;
import model.ExchangeRate;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIService {
    private static final String API_KEY = "YOUR_API_KEY"; // Replace this
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static ExchangeRate fetchRates(String base) {
        try {
            String urlStr = BASE_URL + API_KEY + "/latest/" + base;
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(conn.getInputStream());
                return new Gson().fromJson(reader, ExchangeRate.class);
            } else {
                System.out.println("Error: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
