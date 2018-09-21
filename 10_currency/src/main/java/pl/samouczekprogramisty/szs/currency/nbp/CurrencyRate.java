package pl.samouczekprogramisty.szs.currency.nbp;

import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyRate {

    private LocalDate effectiveDate;
    @JsonbProperty("mid")
    private BigDecimal rate;
    @JsonbProperty("no")
    private String id;

    public CurrencyRate() {
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}