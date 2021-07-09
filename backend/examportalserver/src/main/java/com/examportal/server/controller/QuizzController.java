package com.examportal.server.controller;

import com.examportal.server.models.Quizz;
import com.examportal.server.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/quizzes")
public class QuizzController {

    private QuizzService quizzService;
    @Autowired
    public QuizzController(QuizzService quizzService) {
        this.quizzService = quizzService;
    }

    @GetMapping
    public ResponseEntity<Set<Quizz>> getQuizzes(){
        Set<Quizz> quizzSet=quizzService.getQuizzes();
        return new ResponseEntity<Set<Quizz>>(quizzSet, HttpStatus.OK);
    }

    @GetMapping("/{quizzId}")
    public ResponseEntity<Quizz> getQuizz(@PathVariable(name = "quizzId") Long quizzId){
        Quizz quizz=quizzService.getQuizz(quizzId);
        return new ResponseEntity<Quizz>(quizz, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Quizz> saveQuizz(@RequestBody Quizz quizz){
        Quizz saveQuizz=quizzService.createQuizz(quizz);
        return new ResponseEntity<Quizz>(saveQuizz, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Quizz> updateQuizz(@RequestBody Quizz quizz){
        Quizz updateQuizz=quizzService.updateQuizz(quizz);
        return new ResponseEntity<Quizz>(updateQuizz, HttpStatus.CREATED);
    }

    @DeleteMapping("/{quizzId}")
    public ResponseEntity<?> udeleteQuizz(@PathVariable(name = "quizzId") Long quizzId){
        quizzService.deleteQuizz(quizzId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
