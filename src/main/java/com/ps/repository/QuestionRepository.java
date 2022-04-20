package com.ps.repository;

import com.ps.domain.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository {


    List<Question> getQuestionById(UUID id);
}
