package pl.alex.usersservice.rabbitmq;


import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitMqConfig {

    @Value("${rabbitmq.users.exchange}")
    private String exchangeName;

    @Bean
    public TopicExchange notificationsExchange() {
        return new TopicExchange(exchangeName);
    }

}
