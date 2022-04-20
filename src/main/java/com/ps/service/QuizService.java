package com.ps.service;

import com.ps.domain.Question;
import com.ps.domain.Quiz;
import com.ps.repository.QuizRepository;
import com.ps.repository.QuizRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class QuizService {
    public Quiz createQuiz(Quiz quiz) {
        QuizRepository quizRepository = new QuizRepositoryImpl();
        return quizRepository.createQuiz(quiz);


    }

    public void updateQuiz(Quiz quiz) {
        QuizRepository quizRepository = new QuizRepositoryImpl();
        quizRepository.updateQuiz(quiz);

    }

    public void deleteQuiz(UUID id) {
        QuizRepository quizRepository = new QuizRepositoryImpl();
        quizRepository.deleteQuiz(id);

    }

    public List<Quiz> getQuizBySubject(String subject,String difficulty) {
        QuizRepository quizRepository = new QuizRepositoryImpl();
 return quizRepository.getQuizBySubject(subject,difficulty);
    }



//    public List<Quiz> getQuiz() {
//        QuizRepository quizRepository = new QuizRepositoryImpl();
//        return  quizRepository.getQuiz();
//    }


}
