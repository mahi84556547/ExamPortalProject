package com.examportalserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examportalserver.models.Quizz;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz,Long> {
}
