package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface BookRepository extends BaseRepository<Book, Long>, JpaSpecificationExecutor<Book> {


    @Query(value = "select * from book join " +
                    "(select book_id from schedule where user_id = ?1 and removed = 0) as books " +
                    "on book.book_id = books.book_id",
            nativeQuery = true)
    List<Book> getMyBooks(Long userId);

    @Query(value = "select * from book where book_id = ?1", nativeQuery = true)
    Book getByBookId(Long bookId);
}
