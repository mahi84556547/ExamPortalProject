package com.examportal.server.repositories;

import com.examportal.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUid(String uid);
    User findByUsername(String username);
    User findByEmail(String email);
}
