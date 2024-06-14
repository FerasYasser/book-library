package com.book.library.borrow;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/borrow")
@AllArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @GetMapping
    public ResponseEntity<List<Borrow>> getAll() {
    return borrowService.getAll();
    }
    @PostMapping("{bookId}/parton/{partonId}")
    public ResponseEntity<Borrow> borrow(@PathVariable Long bookId,@PathVariable Long partonId)
    {
        return borrowService.borrow(bookId,partonId);
    }
    @PutMapping("{bookId}/parton/{partonId}")
    public ResponseEntity<Borrow> retrieve(@PathVariable Long bookId,@PathVariable Long partonId)
    {
        return borrowService.retrieve(bookId,partonId);
    }
}
