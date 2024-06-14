package com.book.library.borrow;

import com.book.library.base.Base;
import com.book.library.book.Book;
import com.book.library.parton.Parton;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Borrow extends Base {
    private Long bookId;
    private Long partonId;
    @ManyToOne
    @JoinColumn(name = "bookId",insertable = false,updatable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "partonId",insertable = false,updatable = false)
    private Parton parton;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
}
