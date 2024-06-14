package com.book.library.parton;

import com.book.library.base.Base;
import com.book.library.borrow.Borrow;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Parton extends Base {
    @NotBlank(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String contactInfo;
    @OneToMany(mappedBy = "parton", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Borrow> borrows;
}
