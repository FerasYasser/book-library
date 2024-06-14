package com.book.library.tests;

import com.book.library.borrow.Borrow;
import com.book.library.borrow.BorrowController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BorrowTest {
    @Autowired
    BorrowController  borrowController;
    
    // borrow a book and retrieve
    @Test
    public void testBorrowBook(){
        ResponseEntity<Borrow> borrow = borrowController.borrow(10L, 3L);
        assertEquals(HttpStatus.CREATED,borrow.getStatusCode());
        ResponseEntity<Borrow> retrieve = borrowController.retrieve(10L, 3L);
        assertEquals(HttpStatus.OK,retrieve.getStatusCode());
    }

    // borrow a taken book
    @Test
    public void testTakenBook(){
        ResponseEntity<Borrow> borrow = borrowController.borrow(10L, 3L);
        assertEquals(HttpStatus.CREATED,borrow.getStatusCode());
        ResponseEntity<Borrow> borrow2 = borrowController.borrow(10L, 3L);
        assertEquals(HttpStatus.BAD_REQUEST,borrow2.getStatusCode());
        ResponseEntity<Borrow> retrieve = borrowController.retrieve(10L, 3L);
        assertEquals(HttpStatus.OK,retrieve.getStatusCode());
    }
}
