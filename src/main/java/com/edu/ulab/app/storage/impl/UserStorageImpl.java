package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.storage.UserStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserStorageImpl implements UserStorage {

    private static final List<User> users = new ArrayList<>();

    @Override
    public User saveUser(User user) {
        User newUser = new User();

        newUser.setFullName(user.getFullName());
        newUser.setTitle(user.getTitle());
        newUser.setAge(user.getAge());

        users.add(newUser);

        return newUser;
    }

    @Override
    public void deleteUserById(Long id) {
        User user = getUserById(id);

        users.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        return users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found."));
    }

    @Override
    public User updateUser(User user) {
        User userToUpdate = getUserById(user.getId());

        userToUpdate.setFullName(user.getFullName());
        userToUpdate.setTitle(user.getTitle());
        userToUpdate.setAge(user.getAge());

        int index = users.indexOf(userToUpdate);

        return users.set (index, userToUpdate);
    }
}
