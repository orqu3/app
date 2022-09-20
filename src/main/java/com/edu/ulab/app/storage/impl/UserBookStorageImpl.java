package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.entity.UserBook;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.storage.UserBookStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserBookStorageImpl implements UserBookStorage {

    private static final List<UserBook> usersBooks = new ArrayList<>();

    @Override
    public UserBook assignBookToUser(Long userId, Long bookId) {
        UserBook userBook = new UserBook(userId, bookId);

        usersBooks.add(userBook);

        return userBook;
    }

    @Override
    public void takeOutAllBooksFromUser(Long userId) {
        usersBooks
                .removeIf(userBook -> userBook.getUserId().equals(userId));
    }

    public List<Long> getBookIdsByUserId(Long userId) {
        List<Long> bookIds = usersBooks
                .stream()
                .filter(userBook -> Objects.equals(userBook.getUserId(), userId))
                .map(UserBook::getBookId)
                .toList();
        if (bookIds.isEmpty()) {
            throw new NotFoundException("User with id " + userId + " has no books.");
        }
        return bookIds;
    }
}
