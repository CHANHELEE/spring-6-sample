package tobyspring.hellospring.order;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceTxProxy implements OrderService {
    private final OrderService target;

    private final PlatformTransactionManager platformTransactionManager;

    public OrderServiceTxProxy(OrderService target, PlatformTransactionManager platformTransactionManager) {
        this.target = target;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        return new TransactionTemplate(platformTransactionManager).execute(status -> target.createOrder(no, total));
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(platformTransactionManager).execute(status -> target.createOrders(reqs));
    }
}
