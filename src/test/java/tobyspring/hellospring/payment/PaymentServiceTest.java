package tobyspring.hellospring.payment;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    void convertedAmount() throws Exception {

        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
        testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000));
        testAmount(BigDecimal.valueOf(3_000), BigDecimal.valueOf(30_000));
    }

    private static Payment testAmount(BigDecimal exRate, BigDecimal convertedAmount) {

        //given
        Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        PaymentService paymentService = new PaymentService(
                new ExRateProviderStub(exRate),
                fixedClock
        );

        //when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        //then
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(fixedClock).plusMinutes(30));
        return payment;
    }

}