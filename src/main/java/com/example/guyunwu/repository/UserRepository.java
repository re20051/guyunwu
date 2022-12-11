package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long>, JpaSpecificationExecutor<User> {

    int countByPhoneNumber(String phoneNumber);

    User findByPhoneNumber(String phoneNumber);
}
