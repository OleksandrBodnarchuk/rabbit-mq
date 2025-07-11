package pl.alex.notificationservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RabbitListener(queues = {"${rabbitmq.notifications.queue}"}, group = "${rabbitmq.notifications.group}")
public class NotificationsConsumer {

    private static int count = 0;

    @RabbitHandler
    public void handle(String message) {
        log.debug("[{}] - Received a message: {}", this.getClass().getSimpleName(), message);
        log.debug("[{}] - Total messages: {}", this.getClass().getSimpleName(), ++count);

    }
}
