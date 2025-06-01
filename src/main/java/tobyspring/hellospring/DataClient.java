package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.data.OrderRepository;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] arg) {
        BeanFactory beanFactory = new AnnotationConfigReactiveWebApplicationContext((DataConfig.class));
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        new TransactionTemplate(transactionManager).execute(status -> {
            Order order = new Order("100", BigDecimal.TEN);
            orderRepository.save(order);
            System.out.println(order);

            Order order2 = new Order("101", BigDecimal.TEN);
            orderRepository.save(order2);
            return null;
        });
    }
}
