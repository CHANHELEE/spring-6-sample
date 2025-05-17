package tobyspring.hellospring.exrate;

import tobyspring.hellospring.api.*;
import tobyspring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExPrateProvider implements ExRateProvider {

    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}
