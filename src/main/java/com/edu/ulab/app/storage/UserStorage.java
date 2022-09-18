package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.User;

public interface UserStorage {

    User saveUser(User user);
    void deleteUserById(Long id);
    User getUserById(Long id);
    User updateUser(User user);
}
