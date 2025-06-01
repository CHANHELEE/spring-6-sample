package tobyspring.hellospring.order;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.data.JpaOrderRepository;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final PlatformTransactionManager platformTransactionManager;

    public OrderService(OrderRepository orderRepository, PlatformTransactionManager platformTransactionManager) {
        this.orderRepository = orderRepository;
        this.platformTransactionManager = platformTransactionManager;
    }


    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        return new TransactionTemplate(platformTransactionManager).execute(status -> {
            orderRepository.save(order);
            return order;
        });
    }
}
