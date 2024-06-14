package com.book.library.book;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;


    public ResponseEntity<Page<Book>> getAll(Integer page,Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll(pageRequest));
    }


    public ResponseEntity<Book> getById(Long id)
    {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id " + id));;
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }


    public ResponseEntity<Book> save(Book book)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));
    }


    public ResponseEntity<Book> update(Long id , Book book)
    {
        ResponseEntity<Book> newBook = getById(id);
        book.setId(newBook.getBody().getId());
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(book));
    }

    public ResponseEntity<Void> delete(Long id)
    {
        bookRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
