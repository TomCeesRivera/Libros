package com.myapp.book.service;

import com.myapp.book.dto.BookDTO;

import java.util.List;

public interface IBooksService {

    List<BookDTO> getBooks();

    BookDTO findBook(Long id);

    BookDTO createBook(BookDTO bookDTO);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);
}
