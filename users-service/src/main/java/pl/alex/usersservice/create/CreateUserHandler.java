package pl.alex.usersservice.create;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CreateUserHandler {

    private static final HashMap<String, UserCreatedResponse> users = new HashMap<>();

    ApplicationEventPublisher applicationEventPublisher;

    UserCreatedResponse handle(CreateUserRequest createUserRequest) {
        String userId = UUID.randomUUID().toString();

        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .id(userId)
                .email(createUserRequest.email())
                .build();

        users.put(userId, userCreatedResponse);

        log.debug("[{}] - user created: {}", this.getClass().getSimpleName(), userId);
        applicationEventPublisher.publishEvent(new UserCreatedEvent(userId, createUserRequest.email()));

        return userCreatedResponse;

    }
}
