package pl.samouczekprogramisty.szs.currency;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NbpApi implements Closeable {

    private static final URL API_URL;

    static {
        try {
            API_URL = new URL("http://api.nbp.pl/api/exchangerates/tables/A/");
        } catch (MalformedURLException e) {
            throw new ApiException(e);
        }
    }

    private HttpURLConnection connection;

    public void open() {
        if (connection == null) {
            try {
                connection = (HttpURLConnection) API_URL.openConnection();
            } catch (IOException e) {
                throw new ApiException(e);
            }
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            connection.disconnect();
            connection = null;
        }
    }

    private void validateResponse() {
        try {
            boolean responseCode = connection.getResponseCode() != 200;
            if (responseCode) {
                String responseMessage = connection.getResponseMessage();
                throw new RuntimeException("Something went wrong! " + responseMessage);
            }
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    private String readReponse() {
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

    public Object request() {
        open();
        return readReponse();
    }
}
