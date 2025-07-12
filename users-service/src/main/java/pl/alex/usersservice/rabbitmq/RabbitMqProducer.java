package pl.alex.usersservice.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class RabbitMqProducer {

    @Value("${rabbitmq.user.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.user.created.routing_key}")
    private String usersCreatedRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        log.info("[{}] - sending message to {}: {}", this.getClass().getSimpleName(), exchangeName, message);
        rabbitTemplate.convertAndSend(exchangeName, usersCreatedRoutingKey, message);
    }
}
