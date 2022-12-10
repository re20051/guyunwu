package com.example.guyunwu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<ENTITY, ID> extends JpaRepository<ENTITY, ID> {


}
