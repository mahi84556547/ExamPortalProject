package com.examportalserver.services.impl;

import com.examportalserver.models.Question;
import com.examportalserver.repositories.QuestionRepository;
import com.examportalserver.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Question getQuestionByQuizz(Long quizzId) {
        return questionRepository.findByQuizzId(quizzId);
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Question question=getQuestion(questionId);
        if(question != null){
            questionRepository.deleteById(questionId);
        }
    }
}
