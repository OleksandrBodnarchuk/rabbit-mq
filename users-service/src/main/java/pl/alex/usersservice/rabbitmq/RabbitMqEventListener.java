package pl.alex.usersservice.rabbitmq;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.alex.usersservice.create.UserCreatedEvent;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class RabbitMqEventListener {

    RabbitMqEventHandler rabbitMqEventHandler;

    @EventListener
    public void onUserCreated(UserCreatedEvent userCreatedEvent) {
        log.debug("[{}] - handling user created event for user: {}",
                this.getClass().getSimpleName(),
                userCreatedEvent.userid());
        rabbitMqEventHandler.handlerUserCreatedEvent(userCreatedEvent);
    }
}
