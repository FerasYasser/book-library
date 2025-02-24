package com.book.library.borrow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow,Long> {
    Optional<Borrow> findByBookIdAndPartonIdAndReturnTimeIsNull(Long bookId, Long partonId);
}
