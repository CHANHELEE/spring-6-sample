package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebApiExPrateProvider;
import tobyspring.hellospring.payment.PaymentService;

import java.time.Clock;

@Configuration
@ComponentScan
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExPrateProvider(apiTemplate());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate();
    }

    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }
}
