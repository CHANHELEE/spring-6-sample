package tobyspring.hellospring.exrate;

import tobyspring.hellospring.api.*;
import tobyspring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExPrateProvider implements ExRateProvider {

    private final ApiTemplate apiTemplate;

    public WebApiExPrateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url);
    }
}
