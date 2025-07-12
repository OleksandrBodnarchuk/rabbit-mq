package pl.alex.notificationservice.users.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UsersNotificationsConsumer {

    private static int count = 0;

    ObjectMapper objectMapper;

    @RabbitListener(queues = {"${rabbitmq.user.all.queue}"}, group = "${rabbitmq.user.group}")
    public void handle(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.debug("[{}] - Received a message", this.getClass().getSimpleName());
            log.debug("[{}] - Total messages: {}", this.getClass().getSimpleName(), ++count);
            UserCreatedEvent userCreatedEvent = objectMapper.readValue(message.getBody(), UserCreatedEvent.class);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("[{}] - Failed process the message", this.getClass().getSimpleName());
            channel.basicNack(deliveryTag, false, false);
        }

    }
}
