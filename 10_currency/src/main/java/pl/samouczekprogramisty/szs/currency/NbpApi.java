package pl.samouczekprogramisty.szs.currency;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NbpApi implements Closeable {

    private static final String COURSE_API_URL = "http://api.nbp.pl/api/exchangerates/tables/A/";

    private HttpURLConnection connection;

    public static void main(String ... xxx) throws IOException {
        try (NbpApi api = new NbpApi()) {
            String response = api.request();
        }
        URL url = new URL(COURSE_API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Something went wrong! " + connection.getResponseMessage());
        }
        String response = readReponse(connection);
        connection.disconnect();
    }

    private static String readReponse(HttpURLConnection connection) throws IOException {
        StringBuffer response = new StringBuffer();
        try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line = null;
            while((line = responseReader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    @Override
    public void close() throws IOException {
        connection.disconect();
    }

    public Object request() {
        return null;
    }
}
