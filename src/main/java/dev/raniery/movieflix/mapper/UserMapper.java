package dev.raniery.movieflix.mapper;

import dev.raniery.movieflix.controller.request.UserRequest;
import dev.raniery.movieflix.controller.response.UserResponse;
import dev.raniery.movieflix.entity.Users;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static Users toUser(UserRequest request) {
        return Users.builder()
            .name(request.name())
            .email(request.email())
            .password(request.password())
            .build();
    }

    public static UserResponse toUserResponse(Users user) {
        return UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .build();
    }
}
