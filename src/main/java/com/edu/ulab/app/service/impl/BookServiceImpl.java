package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.BookStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookStorage bookStorage;

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);

        Book newBook = bookStorage.saveBook(book);

        return bookMapper.bookToBookDto(newBook, bookDto.getUserId());
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book updatesForBook = bookMapper.bookDtoToBook(bookDto);

        Book updatedBook = bookStorage.updateBook(updatesForBook);

        return bookMapper.bookToBookDto(updatedBook, bookDto.getUserId());
    }

    @Override
    public BookDto getBookById(Long bookId, Long userId) {
        return bookMapper
                .bookToBookDto(bookStorage.getBookById(bookId), userId);
    }

    @Override
    public void deleteBookById(Long id) {
        bookStorage.deleteBookById(id);
    }
}
