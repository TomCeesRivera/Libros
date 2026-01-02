package com.myapp.book.controller;

import com.myapp.book.dto.BookDTO;
import com.myapp.book.service.IBooksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private IBooksService service;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks(){
        return ResponseEntity.ok(service.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findBook(@PathVariable Long id){
        return ResponseEntity.ok(service.findBook(id));
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO){
        BookDTO createdBook = service.createBook(bookDTO);

        return ResponseEntity
                .created(URI.create("/books/" + createdBook.getId()))
                .body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO){
        BookDTO updatedBook = service.updateBook(id, bookDTO);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        service.deleteBook(id);

        return ResponseEntity.noContent().build();
    }
}
