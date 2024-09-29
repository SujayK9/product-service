package com.backend.productservice.dtos;

import com.backend.productservice.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String fullname;

    public static UserDto from(User user) {
        if(user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.fullname = user.getFullname();
        return userDto;
    }

}
