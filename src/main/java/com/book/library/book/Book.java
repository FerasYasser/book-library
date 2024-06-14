package com.book.library.book;

import com.book.library.base.Base;
import com.book.library.borrow.Borrow;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Book extends Base {
    @Column(unique = true)
    @NotBlank(message = "Title is mandatory")
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Author is mandatory")
    @NotBlank(message = "Author is mandatory")
    private String author;
    private Integer publicationYear;
    private String isbn;
    @Builder.Default
    private Boolean isReserved = false;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Borrow> borrows;


}
