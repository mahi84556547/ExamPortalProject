package com.examportalserver.controller;

import com.examportalserver.models.Question;
import com.examportalserver.models.Quizz;
import com.examportalserver.services.QuestionService;
import com.examportalserver.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("questions")
public class QuestionController {

    private QuestionService questionService;
    private QuizzService quizzService;
    
    @Autowired
    public QuestionController(QuestionService questionService, QuizzService quizzService) {
        this.questionService = questionService;
        this.quizzService=quizzService;
    }

    @GetMapping
    public ResponseEntity<Set<Question>> getQuestions(){
        Set<Question> questionSet=questionService.getQuestions();
        return new ResponseEntity<Set<Question>>(questionSet, HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable(name = "questionId") Long questionId){
        Question question=questionService.getQuestion(questionId);
        return new ResponseEntity<Question>(question, HttpStatus.OK);
    }

    @GetMapping("quizz/{quizzId}")
    public ResponseEntity<List<Question>> getQuestionByQuizzId(@PathVariable(name = "quizzId") Long quizzId){
        Quizz quizz=this.quizzService.getQuizz(quizzId);
        Set<Question> questions=quizz.getQuestionSet();
        List list=new ArrayList(questions);
        if(list.size()>Integer.parseInt(quizz.getNumberOfQuestions())){
            list=list.subList(0,Integer.parseInt(quizz.getNumberOfQuestions() + 1));
        }
        Collections.shuffle(list);
        //List<Question>  questions=questionService.getQuestionByQuizz(quizzId);
        return new ResponseEntity<List<Question> >(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question){
        Question saveQuestion=questionService.createQuestion(question);
        return new ResponseEntity<Question>(saveQuestion, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        Question updateQuestion=questionService.updateQuestion(question);
        return new ResponseEntity<Question>(updateQuestion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(name = "questionId") Long questionId){
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
