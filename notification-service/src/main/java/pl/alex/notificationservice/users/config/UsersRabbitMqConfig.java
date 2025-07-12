package pl.alex.notificationservice.users.config;


import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UsersRabbitMqConfig {

    private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    @Bean
    public Queue allUsersQueue(RabbitMqProperties properties) {
        return QueueBuilder.durable(properties.getUsersAllQueue())
                .withArgument(X_DEAD_LETTER_EXCHANGE, properties.getDltExchange())
                .withArgument(X_DEAD_LETTER_ROUTING_KEY, properties.getUsersDltKey())
                .build();
    }

    @Bean
    public TopicExchange usersTopicExchange(RabbitMqProperties properties) {
        return new TopicExchange(properties.getUsersAllExchange());
    }

    @Bean
    public Binding allUsersBinding(RabbitMqProperties properties) {
        return BindingBuilder.bind(allUsersQueue(properties))
                .to(usersTopicExchange(properties))
                .with(properties.getUsersAllRoutingKey());
    }

    @Bean
    @ConfigurationProperties(prefix = "rabbitmq")
    public RabbitMqProperties rabbitMqProperties() {
        return new RabbitMqProperties();
    }
}
