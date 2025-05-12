import com.google.gson.Gson;
import model.ConversionOption;
import model.ExchangeResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {

    private static final String API_KEY = System.getenv("EXCHANGE_API_KEY");
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
    private final HttpClient client = HttpClient.newHttpClient();

    public double getRateFor(ConversionOption option) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("️ API error: HTTP status " + response.statusCode());
                return -1;
            }

            ExchangeResponse exchangeResponse = new Gson().fromJson(response.body(), ExchangeResponse.class);
            Double rate = exchangeResponse.getConversion_rates().get(option.target());

            if (rate == null) {
                System.out.println(" Target currency not found in response.");
                return -1;
            }

            return option.inverse() ? 1 / rate : rate;

        } catch (Exception e) {
            System.out.println("️ Error connecting to API: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return -1;
        }
    }
}
