package com.edu.ulab.app.mapper;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.web.request.UserRequest;
import com.edu.ulab.app.web.request.UserUpdateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userRequestToUserDto(UserRequest userRequest);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    UserDto userUpdateRequestToUserDto(UserUpdateRequest userUpdateRequest);
}
