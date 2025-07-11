package pl.alex.usersservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.notifications.queue}")
    private String notificationsQueue;

    @Value("${rabbitmq.notifications.exchange_name}")
    private String exchangeName;

    @Value("${rabbitmq.notifications.routing_key}")
    private String notificationsRoutingKey;

    @Bean
    public Queue notificationsQueue() {
        return new Queue(notificationsQueue);
    }

    @Bean
    public TopicExchange notificationsExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(notificationsQueue()).to(notificationsExchange()).with(notificationsRoutingKey);
    }
}
