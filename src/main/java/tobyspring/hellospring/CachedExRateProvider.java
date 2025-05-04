package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider exRateProvider;

    private BigDecimal cachedExRate;

    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(ExRateProvider exRateProvider) {
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
