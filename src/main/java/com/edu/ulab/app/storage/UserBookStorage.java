package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.UserBook;

import java.util.List;


public interface UserBookStorage {

    UserBook assignBookToUser(Long userId, Long bookId);

    void takeOutAllBooksFromUser(Long userId);

    List<Long> getBookIdsByUserId(Long userId);
}
