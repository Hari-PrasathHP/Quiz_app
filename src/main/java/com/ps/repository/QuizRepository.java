package com.ps.repository;

import com.ps.domain.Question;
import com.ps.domain.Quiz;

import java.util.List;
import java.util.UUID;

public interface QuizRepository {
    Quiz createQuiz(Quiz quiz);

    void updateQuiz(Quiz quiz);

    void deleteQuiz(UUID id);

   // List<Quiz> getQuiz();

    List<Quiz> getQuizBySubject(String subject,String difficulty);
}
