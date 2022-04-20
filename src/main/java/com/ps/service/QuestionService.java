package com.ps.service;


import com.ps.domain.Question;
import com.ps.repository.QuestionRepository;
import com.ps.repository.QuestionRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import java.util.UUID;

public class QuestionService  {
private static final Logger logger = LogManager.getLogger(QuestionService.class);

    public List<Question> getQuestionById(UUID id) {
        logger.info("In the question service method");
        QuestionRepository questionRepository = new QuestionRepositoryImpl();
      return  questionRepository.getQuestionById(id);
    }
}
