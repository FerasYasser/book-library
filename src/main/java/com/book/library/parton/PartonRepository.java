package com.book.library.parton;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartonRepository extends JpaRepository<Parton,Long> {
}
