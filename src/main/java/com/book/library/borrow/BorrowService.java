package com.book.library.borrow;

import com.book.library.book.Book;
import com.book.library.book.BookService;
import com.book.library.parton.Parton;
import com.book.library.parton.PartonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final BookService bookService;
    private final PartonService partonService;
    public ResponseEntity<List<Borrow>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(borrowRepository.findAll());
    }
    @Transactional
    public ResponseEntity<Borrow> borrow(Long bookId, Long partonId) {
        ResponseEntity<Book> book = bookService.getById(bookId);
        ResponseEntity<Parton> parton = partonService.getById(partonId);
        if(book.getBody().getIsReserved())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Borrow borrow = new Borrow();
        borrow.setBookId(bookId);
        borrow.setPartonId(partonId);
        borrow.setBorrowTime(LocalDateTime.now());
        book.getBody().setIsReserved(true);
        borrowRepository.save(borrow);
        bookService.save(book.getBody());
        return ResponseEntity.status(HttpStatus.CREATED).body(borrow);
    }

    @Transactional
    public ResponseEntity<Borrow> retrieve(Long bookId, Long partonId) {
        ResponseEntity<Book> book = bookService.getById(bookId);
        Borrow borrow = borrowRepository.findByBookIdAndPartonIdAndReturnTimeIsNull(bookId,partonId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrow not found"));
        borrow.setReturnTime(LocalDateTime.now());
        book.getBody().setIsReserved(false);
        borrowRepository.save(borrow);
        bookService.save(book.getBody());
        return ResponseEntity.status(HttpStatus.OK).body(borrow);
    }
}
