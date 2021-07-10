package com.examportalserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportalserver.models.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByQuizzId(Long quizzId);
}
