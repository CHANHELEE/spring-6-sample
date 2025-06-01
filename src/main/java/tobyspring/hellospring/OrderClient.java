package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderService;

import java.math.BigDecimal;

public class OrderClient {

    public static void main(String[] arg) {
        BeanFactory beanFactory = new AnnotationConfigReactiveWebApplicationContext((OrderConfig.class));
        OrderService orderService = beanFactory.getBean(OrderService.class);

        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        System.out.println(order);
    }
}
