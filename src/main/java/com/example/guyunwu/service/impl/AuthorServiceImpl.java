package com.example.guyunwu.service.impl;

import com.example.guyunwu.model.entity.Author;
import com.example.guyunwu.repository.AuthorRepository;
import com.example.guyunwu.service.AuthorService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends AbstractCrudService<Author, Long> implements AuthorService {

    protected AuthorServiceImpl (AuthorRepository authorRepository) {
        super(authorRepository);
        this.authorRepository = authorRepository;
    }

    private final AuthorRepository authorRepository;


    @Override
    public Author getAuthor(Long authorId) {
        return authorRepository.getById(authorId);
    }
}
