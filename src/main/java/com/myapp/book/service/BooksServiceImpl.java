package com.myapp.book.service;

import com.myapp.book.dto.BookDTO;
import com.myapp.book.exception.BookNotFoundException;
import com.myapp.book.mapper.Mapper;
import com.myapp.book.model.Book;
import com.myapp.book.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements IBooksService{

    @Autowired
    private BooksRepository repository;

    @Override
    public List<BookDTO> getBooks() {
        return repository.findAll().stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public BookDTO findBook(Long id) {
        return repository.findById(id)
                .map(Mapper::toDto)
                .orElseThrow(() ->
                        new BookNotFoundException("No se encuentra el libro solicitado")
                );
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = Book.builder()
                        .title(bookDTO.getTitle())
                        .author(bookDTO.getAuthor())
                        .isbn(bookDTO.getIsbn())
                        .publishedYear(bookDTO.getPublishedYear())
                        .price(bookDTO.getPrice())
                        .build();

        return Mapper.toDto(repository.save(book));
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = repository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("No se encuentra el libro solicitado")
                );
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublishedYear(bookDTO.getPublishedYear());
        book.setPrice(bookDTO.getPrice());

        return Mapper.toDto(repository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        if (!repository.existsById(id)){
            throw new BookNotFoundException("El libro a eliminar no existe");
        }

        repository.deleteById(id);
    }
}
