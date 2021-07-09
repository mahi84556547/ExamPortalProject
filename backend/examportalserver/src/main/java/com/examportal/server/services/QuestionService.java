package com.examportal.server.services;

import com.examportal.server.models.Question;

import java.util.Set;

public interface QuestionService {
    public Set<Question> getQuestions();
    public Question getQuestion(Long questionId);
    public Question createQuestion(Question question);
    public Question updateQuestion(Question question);
    public void deleteQuestion(Long questionId);
}
