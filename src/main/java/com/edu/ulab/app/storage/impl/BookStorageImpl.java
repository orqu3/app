package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.storage.BookStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookStorageImpl implements BookStorage {

    private static final List<Book> books = new ArrayList<>();

    @Override
    public Book saveBook(Book book) {
        Book newBook = new Book();

        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setPageCount(book.getPageCount());

        books.add(newBook);

        return newBook;
    }

    @Override
    public void deleteBookById(Long id) {
        Book book = getBookById(id);

        books.remove(book);
    }

    @Override
    public Book getBookById(Long id) {
        return books
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found."));
    }

    @Override
    public Book updateBook(Book book) {
        Book bookToUpdate = getBookById(book.getId());

        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setPageCount(book.getPageCount());

        int index = books.indexOf(bookToUpdate);

        return books.set (index, bookToUpdate);
    }
}
