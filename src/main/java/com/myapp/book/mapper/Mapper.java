package com.myapp.book.mapper;

import com.myapp.book.dto.BookDTO;
import com.myapp.book.model.Book;

public class Mapper {

    public static BookDTO toDto(Book book){
        if(book == null) return null;

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publishedYear(book.getPublishedYear())
                .price(book.getPrice())
                .build();
    }
}
