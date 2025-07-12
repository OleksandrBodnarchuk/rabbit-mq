package pl.alex.notificationservice.users.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UsersRabbitMqConfig {

    @Value("${rabbitmq.users.all.queue}")
    private String usersAllQueue;

    @Value("${rabbitmq.users.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.users.all.routing_key}")
    private String usersAllRoutingKey;

    @Bean
    public Queue allUsersQueue() {
        return new Queue(usersAllQueue, true);
    }

    @Bean
    public Binding allUusersBinding() {
        return BindingBuilder.bind(allUsersQueue()).to(usersTopicExchange()).with(usersAllRoutingKey);
    }

    @Bean
    public TopicExchange usersTopicExchange() {
        return new TopicExchange(exchangeName);
    }

}
