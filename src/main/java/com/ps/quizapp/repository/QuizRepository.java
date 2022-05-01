package com.ps.quizapp.repository;


import com.ps.quizapp.domain.Quiz;

import java.util.List;
import java.util.UUID;


public interface QuizRepository {
    Quiz createQuiz(Quiz quiz);

    Quiz updateQuiz(Quiz quiz);

    void deleteQuiz(UUID id);

   List<Quiz> getQuizBySubject(String subject,String difficulty);
}
