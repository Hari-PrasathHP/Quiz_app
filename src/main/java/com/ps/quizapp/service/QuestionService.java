package com.ps.quizapp.service;

import com.ps.quizapp.domain.Question;
import com.ps.quizapp.repository.QuestionRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService  {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionRepositoryImpl questionRepository;


@Autowired
    public QuestionService(QuestionRepositoryImpl questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Transactional
    public List<Question> getQuestionById(UUID id) {
        logger.info("In the question service method");
              return  questionRepository.getQuestionById(id);
    }

    @Transactional
    public void deleteQuestion(UUID id) {
          questionRepository.deleteQuestion(id);
    }
}
