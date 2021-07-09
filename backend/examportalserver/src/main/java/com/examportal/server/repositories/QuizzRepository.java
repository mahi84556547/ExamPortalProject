package com.examportal.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportal.server.models.Quizz;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz,Long> {
}
