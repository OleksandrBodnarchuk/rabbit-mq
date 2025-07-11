package pl.alex.usersservice.create;

import lombok.Builder;

@Builder
public record UserCreatedResponse(String id, String email) {
}
