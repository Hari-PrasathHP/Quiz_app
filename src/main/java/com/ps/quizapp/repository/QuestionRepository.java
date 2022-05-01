package com.ps.quizapp.repository;


import com.ps.quizapp.domain.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository {
    void deleteQuestion(UUID id);

    List<Question> getQuestionById(UUID id);
}
