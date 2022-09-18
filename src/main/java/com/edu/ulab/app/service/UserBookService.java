package com.edu.ulab.app.service;

import com.edu.ulab.app.entity.UserBook;

import java.util.List;

public interface UserBookService {

    UserBook assignBookToUser(Long userId, Long bookId);

    void takeOutAllBooksFromUser(Long userId);

    List<Long> getBookIdsByUserId(Long userId);
}
