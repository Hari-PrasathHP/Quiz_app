package com.ps.quizapp.service;


import com.ps.quizapp.domain.Quiz;
import com.ps.quizapp.repository.QuizRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class QuizService {

    private static final Logger logger = LogManager.getLogger(QuizService.class);


    private final QuizRepositoryImpl quizRepository;

    @Autowired
    public QuizService(QuizRepositoryImpl quizRepository) {
        this.quizRepository = quizRepository;
    }


    @Transactional
    public Quiz createQuiz(Quiz quiz) {

        return quizRepository.createQuiz(quiz);

    }

    @Transactional
    public Quiz updateQuiz(Quiz quiz) {
       return quizRepository.updateQuiz(quiz);

    }

    @Transactional
    public void deleteQuiz(UUID id) {
        quizRepository.deleteQuiz(id);

    }

    @Transactional
    public List<Quiz> getQuizBySubject(String subject, String difficulty) {
        logger.info("In the getQUizBySubject service method");
        return quizRepository.getQuizBySubject(subject, difficulty);
    }



}
