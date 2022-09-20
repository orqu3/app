package com.edu.ulab.app.facade;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserBook;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.service.UserBookService;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.web.request.UserBookRequest;
import com.edu.ulab.app.web.request.UserBookUpdateRequest;
import com.edu.ulab.app.web.response.UserBookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDataFacade {
    private final UserService userService;
    private final BookService bookService;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final UserBookService userBookService;


    public UserBookResponse createUserWithBooks(UserBookRequest userBookRequest) {
        log.info("Got user book create request: {}", userBookRequest);
        UserDto userDto = userMapper.userRequestToUserDto(userBookRequest.getUserRequest());
        log.info("Mapped user request: {}", userDto);

        UserDto createdUser = userService.createUser(userDto);
        log.info("Created user: {}", createdUser);

        List<Long> bookIdList = userBookRequest.getBookRequests()
                .stream()
                .filter(Objects::nonNull)
                .map(bookMapper::bookRequestToBookDto)
                .peek(bookDto -> bookDto.setUserId(createdUser.getId()))
                .peek(mappedBookDto -> log.info("mapped book: {}", mappedBookDto))
                .map(bookService::createBook)
                .peek(createdBookDto -> log.info("Created book: {}", createdBookDto))
                .map(BookDto::getId)
                .toList();
        log.info("Collected book ids: {}", bookIdList);

        List<UserBook> userBooks = bookIdList
                .stream()
                .map(bookId -> userBookService.assignBookToUser(createdUser.getId(), bookId))
                .toList();
        log.info("Assigned to user books: {}", userBooks);

        return UserBookResponse.builder()
                .userId(createdUser.getId())
                .booksIdList(bookIdList)
                .build();
    }

    public UserBookResponse updateUserWithBooks(UserBookUpdateRequest userBookUpdateRequest) {
        log.info("Got user book update request: {}", userBookUpdateRequest);

        UserDto userDto = userMapper
                .userUpdateRequestToUserDto(userBookUpdateRequest.getUserUpdateRequest());
        log.info("Mapped user update request: {}", userDto);

        UserDto updatedUser = userService.updateUser(userDto);

        List<Long> bookIdList = userBookUpdateRequest.getBookUpdateRequests()
                .stream()
                .filter(Objects::nonNull)
                .map(bookMapper::bookUpdateRequestToBookDto)
                .map(bookService::updateBook)
                .map(BookDto::getId)
                .toList();
        log.info("Collected book ids: {}", bookIdList);

        return UserBookResponse.builder()
                .userId(updatedUser.getId())
                .booksIdList(bookIdList)
                .build();
    }

    public UserBookResponse getUserWithBooks(Long userId) {

        UserDto userWithBooks = userService.getUserById(userId);
        List<Long> bookIds = userBookService.getBookIdsByUserId(userId);

        log.info("Assigned to user " + userId + " books: {}", bookIds);

        return UserBookResponse.builder()
                .userId(userWithBooks.getId())
                .booksIdList(bookIds)
                .build();
    }

    public void deleteUserWithBooks(Long userId) {
        userBookService.takeOutAllBooksFromUser(userId);

        log.info("All books were took out from user: {}", userId);

        userService.deleteUserById(userId);

        log.info("User with id " + userId + " was deleted.");
    }
}
