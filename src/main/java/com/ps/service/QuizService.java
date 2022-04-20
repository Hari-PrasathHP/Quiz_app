package com.ps.service;

import com.ps.domain.Question;
import com.ps.domain.Quiz;
import com.ps.repository.QuizRepository;
import com.ps.repository.QuizRepositoryImpl;
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

    @Autowired
    private QuizRepositoryImpl quizRepository;

    public Quiz createQuiz(Quiz quiz) {

        return quizRepository.createQuiz(quiz);

    }

    public void updateQuiz(Quiz quiz) {
        quizRepository.updateQuiz(quiz);

    }

    public void deleteQuiz(UUID id) {
        quizRepository.deleteQuiz(id);

    }

    @Transactional
    public List<Quiz> getQuizBySubject(String subject, String difficulty) {
        logger.info("In the getQUizBySubject service method");
        return quizRepository.getQuizBySubject(subject, difficulty);
    }


//    public List<Quiz> getQuiz() {
//        QuizRepository quizRepository = new QuizRepositoryImpl();
//        return  quizRepository.getQuiz();
//    }


}
