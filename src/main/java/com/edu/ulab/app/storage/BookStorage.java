package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;

public interface BookStorage {

    Book saveBook(Book book);
    void deleteBookById(Long id);
    Book getBookById(Long id);

    Book updateBook(Book book);
}
