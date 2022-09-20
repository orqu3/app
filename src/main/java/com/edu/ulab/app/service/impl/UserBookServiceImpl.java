package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.entity.UserBook;
import com.edu.ulab.app.service.UserBookService;
import com.edu.ulab.app.storage.UserBookStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBookServiceImpl implements UserBookService {

    private final UserBookStorage userBookStorage;

    @Override
    public UserBook assignBookToUser(Long userId, Long bookId) {
       return userBookStorage.assignBookToUser(userId, bookId);
    }

    @Override
    public void takeOutAllBooksFromUser(Long userId) {
        userBookStorage.takeOutAllBooksFromUser(userId);
    }

    @Override
    public List<Long> getBookIdsByUserId(Long userId) {
        return userBookStorage.getBookIdsByUserId(userId);
    }
}
