package pl.samouczekprogramisty.szs.currency;

import pl.samouczekprogramisty.szs.currency.nbp.CurrencyDetails;
import pl.samouczekprogramisty.szs.currency.nbp.CurrencyRate;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class NbpApi {

    private static final String API_URL_TEMPLATE = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s";
    private static Jsonb jsonb = JsonbBuilder.create();

    private static class HttpConnection implements Closeable {

        private final HttpURLConnection connection;

        private HttpConnection(String url) {
            try {
                connection = (HttpURLConnection) new URL(url).openConnection();
            } catch (IOException e) {
                throw new ApiException(e);
            }
        }

        String response() {
            validateResponse();
            StringBuilder response = new StringBuilder();
            try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while((line = responseReader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                throw new ApiException(e);
            }
            return response.toString();
        }

        @Override
        public void close() {
            connection.disconnect();
        }

        private void validateResponse() {
            try {
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    String responseMessage = connection.getResponseMessage();
                    throw new ApiException(String.format("Something went wrong! [%d] %s", responseCode, responseMessage));
                }
            } catch (IOException e) {
                throw new ApiException(e);
            }
        }

    }

    public BigDecimal exchangeRate(LocalDate date, String currencyCode) {
        String requestUrl = String.format(API_URL_TEMPLATE, currencyCode, date.toString());
        try (HttpConnection connection = new HttpConnection(requestUrl)) {
            String response = connection.response();
            CurrencyDetails currencyDetails = jsonb.fromJson(response, CurrencyDetails.class);
            CurrencyRate currencyRate = currencyDetails.getRates().get(0);
            return currencyRate.getRate();
        }
    }

}
