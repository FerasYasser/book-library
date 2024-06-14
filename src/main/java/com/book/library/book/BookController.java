package com.book.library.book;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
@Validated
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<Book>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size){
        return bookService.getAll(page,size);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id)
    {
        return bookService.getById(id) ;
    }
    @PostMapping
    public ResponseEntity<Book> save(@Valid @RequestBody Book book)
    {
        return bookService.save(book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id , @RequestBody Book book)
    {
        return bookService.update(id,book);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        return bookService.delete(id);
    }

}
