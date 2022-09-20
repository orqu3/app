package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserStorage userStorage;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);

        User newUser = userStorage.saveUser(user);

        return userMapper.userToUserDto(newUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User updatesForUser = userMapper.userDtoToUser(userDto);

        User updatedUser = userStorage.updateUser(updatesForUser);

        return userMapper.userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper
                .userToUserDto(userStorage.getUserById(id));
    }

    @Override
    public void deleteUserById(Long id) {
        userStorage.deleteUserById(id);
    }

}
