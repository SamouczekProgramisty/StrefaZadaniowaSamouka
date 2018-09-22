package pl.samouczekprogramisty.szs.currency;

import pl.samouczekprogramisty.szs.currency.nbp.CurrencyDetails;
import pl.samouczekprogramisty.szs.currency.nbp.CurrencyRate;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NbpApi implements ExchangeApi {

    private static final String API_URL_TEMPLATE = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s";

    private final ConnectionFactory connectionFactory;
    private final Jsonb jsonb;

    public NbpApi() {
        this(new ConnectionFactory(), JsonbBuilder.create());
    }

    public NbpApi(ConnectionFactory connectionFactory, Jsonb jsonb) {
        this.connectionFactory = connectionFactory;
        this.jsonb = jsonb;
    }

    @Override
    public BigDecimal exchangeRate(LocalDate date, String currencyCode) {
        String requestUrl = String.format(API_URL_TEMPLATE, currencyCode, date.toString());
        try (HttpConnection connection = connectionFactory.build(requestUrl)) {
            String response = connection.response();
            CurrencyDetails currencyDetails = jsonb.fromJson(response, CurrencyDetails.class);
            CurrencyRate currencyRate = currencyDetails.getRates().get(0);
            return currencyRate.getRate();
        }
    }
}
