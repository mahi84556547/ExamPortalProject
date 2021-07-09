package com.examportal.server.services.impl;

import com.examportal.server.models.Quizz;
import com.examportal.server.repositories.QuizzRepository;
import com.examportal.server.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class QuizzServiceImpl implements QuizzService {

    private QuizzRepository quizzRepository;

    @Autowired
    public QuizzServiceImpl(QuizzRepository quizzRepository) {
        this.quizzRepository = quizzRepository;
    }

    @Override
    public Set<Quizz> getQuizzes() {
        return new HashSet<>(quizzRepository.findAll());
    }

    @Override
    public Quizz getQuizz(Long quizzId) {
        return quizzRepository.findById(quizzId).get();
    }

    @Override
    public Quizz createQuizz(Quizz quizz) {
        return quizzRepository.save(quizz);
    }

    @Override
    public Quizz updateQuizz(Quizz quizz) {
        return quizzRepository.save(quizz);
    }

    @Override
    public void deleteQuizz(Long quizzId) {
        Quizz quizz=getQuizz(quizzId);
        if(quizz!=null){
            quizzRepository.deleteById(quizzId);
        }
    }
}
