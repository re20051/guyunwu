package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.Author;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends BaseRepository<Author, Long>, JpaSpecificationExecutor<Author> {
}
