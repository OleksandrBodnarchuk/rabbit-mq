package pl.alex.usersservice.create;

import lombok.Builder;

@Builder
public record UserCreatedEvent(String userid, String email) {
}
