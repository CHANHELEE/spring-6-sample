package tobyspring.hellospring.exrate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import tobyspring.hellospring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Primary
public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider exRateProvider;

    private BigDecimal cachedExRate;

    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(@Qualifier("webApiExPrateProvider") ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {

        if (cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExRate = exRateProvider.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("Cache Updated");
        }

        return cachedExRate;
    }
}
