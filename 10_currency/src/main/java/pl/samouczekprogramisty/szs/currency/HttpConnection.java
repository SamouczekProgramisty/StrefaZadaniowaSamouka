package pl.samouczekprogramisty.szs.currency;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection implements Closeable {

    private final HttpURLConnection connection;

    HttpConnection(String url) {
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
            while ((line = responseReader.readLine()) != null) {
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

    void validateResponse() {
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
