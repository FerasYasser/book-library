package com.book.library.tests;

import com.book.library.book.Book;
import com.book.library.book.BookController;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BookTest {

    @Autowired
    BookController bookController;

    // creating , getting and deleting by id
    @Test
    public void testBookCreation() {
        Book book = new Book();
        book.setAuthor("feras");
        book.setTitle("book5");
        book.setIsbn("!23445");
        ResponseEntity<Book> createdBook= bookController.save(book);
        assertEquals(HttpStatus.CREATED,  createdBook.getStatusCode());
        ResponseEntity<Book> getBookById = bookController.getById(createdBook.getBody().getId());
        assertEquals(getBookById.getBody().getId(),createdBook.getBody().getId());
        ResponseEntity<Void> deleteBook = bookController.delete(getBookById.getBody().getId());
        assertThrows(ResponseStatusException.class, () -> {
            bookController.getById(createdBook.getBody().getId());
        });
    }


    @Test
    public void testBookCreationUniqueTitle() {
        Book book = new Book();
        book.setAuthor("feras");
        book.setTitle("book5");
        book.setIsbn("!23445");
        ResponseEntity<Book> createdBook= bookController.save(book);
        assertEquals(HttpStatus.CREATED,  createdBook.getStatusCode());

        Book book2 = new Book();
        book2.setAuthor("feras1223");
        book2.setTitle("book5");
        book2.setIsbn("!2344555211");
        assertThrows(DataIntegrityViolationException.class, () -> {
            bookController.save(book2);
        });
        ResponseEntity<Void> deleteBook = bookController.delete(createdBook.getBody().getId());

    }


    // creating with blank title
    @Test
    public void testBookCreationWithBlankTitle() {
        Book book = new Book();
        book.setAuthor("feras");
        book.setTitle("");
        book.setIsbn("!23445");
        assertThrows(ConstraintViolationException.class, () -> {
            bookController.save(book);
        });
    }

    // creating with null title
    @Test
    public void testBookCreationWithNullTitle() {
        Book book = new Book();
        book.setAuthor("feras");
        book.setTitle(null);
        book.setIsbn("!23445");
        assertThrows(ConstraintViolationException.class, () -> {
            bookController.save(book);
        });
    }

    // test book udpate
    @Test
    public void testBookUpdate() {
        Book book = new Book();
        book.setAuthor("feras100");
        book.setTitle("book0005");
        book.setIsbn("1123445");
        ResponseEntity<Book> updatedBook= bookController.update(2L,book);
        assertEquals(HttpStatus.OK,  updatedBook.getStatusCode());
        ResponseEntity<Book> getBookById = bookController.getById(updatedBook.getBody().getId());
        assertEquals(getBookById.getBody().getAuthor(),updatedBook.getBody().getAuthor());
        assertEquals(getBookById.getBody().getTitle(),updatedBook.getBody().getTitle());
        assertEquals(getBookById.getBody().getIsbn(),updatedBook.getBody().getIsbn());
    }
}

