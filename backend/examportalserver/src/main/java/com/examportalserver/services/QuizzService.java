package com.examportalserver.services;

import com.examportalserver.models.Quizz;

import java.util.Set;

public interface QuizzService {
    public Set<Quizz> getQuizzes();
    public Quizz getQuizz(Long quizzId);
    public Quizz createQuizz(Quizz quizz);
    public Quizz updateQuizz(Quizz quizz);
    public void deleteQuizz(Long quizzId);
}
