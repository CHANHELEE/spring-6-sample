package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tobyspring.hellospring.exrate.ExRateData;

import java.math.BigDecimal;

public class ErApiExRateExtractor implements ExRateExtractor{
    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        ExRateData data;
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
