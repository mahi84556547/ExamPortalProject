package com.examportalserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportalserver.models.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    Question findByQuizzId(Long quizzId);
}
