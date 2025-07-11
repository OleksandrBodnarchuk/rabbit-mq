package pl.alex.usersservice.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.alex.usersservice.create.UserCreatedEvent;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class RabbitMqEventHandler {

    RabbitMqProducer rabbitMqProducer;
    ObjectMapper objectMapper;

    @SneakyThrows
    public void handlerUserCreatedEvent(UserCreatedEvent userCreatedEvent) {

        log.debug("[{}] - handling user created event for user: {}",
                this.getClass().getSimpleName(),
                userCreatedEvent.userid());

        rabbitMqProducer.sendMessage(objectMapper.writeValueAsString(userCreatedEvent));
    }
}
