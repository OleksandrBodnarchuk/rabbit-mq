package pl.alex.notificationservice.users.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UsersDltRabbitMqConfig {

    @Bean
    public Queue usersDltQueue(RabbitMqProperties properties) {
        return QueueBuilder.durable(properties.getUsersDltQueue())
                .withArgument("x-dead-letter-exchange", properties.getUsersAllExchange())
                .withArgument("x-dead-letter-routing-key", properties.getUsersAllRoutingKey())
                .withArgument("x-message-ttl", properties.getDtlTtl())
                .build();
    }

    @Bean
    public TopicExchange usersDltExchange(RabbitMqProperties properties) {
        return new TopicExchange(properties.getDltExchange());
    }

    @Bean
    public Binding usersDltBinding(RabbitMqProperties properties) {
        return BindingBuilder.bind(usersDltQueue(properties))
                .to(usersDltExchange(properties))
                .with(properties.getUsersDltKey());
    }


}
