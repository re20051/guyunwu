package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long>, JpaSpecificationExecutor<User> {

    int countByPhoneNumber(String phoneNumber);

    User findByPhoneNumber(String phoneNumber);

    @Query(value = "select * from user where user_id = ?1", nativeQuery = true)
    Optional<Object> findByUserId(Long userId);
}
