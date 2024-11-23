package com.backend.productservice.dtos;

import com.backend.productservice.model.Role;
import com.backend.productservice.model.User;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;

    public static UserDto from(User user) {
        if(user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.name = user.getFullname();
        userDto.roles = user.getRoles();
        return userDto;
    }

}
