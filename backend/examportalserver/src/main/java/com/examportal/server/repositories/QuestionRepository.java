package com.examportal.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportal.server.models.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
