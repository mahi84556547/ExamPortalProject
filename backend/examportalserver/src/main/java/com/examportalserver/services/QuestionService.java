package com.examportalserver.services;

import com.examportalserver.models.Question;
import com.examportalserver.models.Quizz;

import java.util.Set;

public interface QuestionService {
    public Set<Question> getQuestions();
    public Question getQuestion(Long questionId);
    public Question getQuestionByQuizz(Long quizzId);
    public Question createQuestion(Question question);
    public Question updateQuestion(Question question);
    public void deleteQuestion(Long questionId);
}
