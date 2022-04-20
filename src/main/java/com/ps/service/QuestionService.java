package com.ps.service;


import com.ps.domain.Question;
import com.ps.repository.QuestionRepository;
import com.ps.repository.QuestionRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;
@Service
public class QuestionService  {

    @Autowired
    private QuestionRepositoryImpl questionRepository;

private static final Logger logger = LogManager.getLogger(QuestionService.class);

@Transactional
    public List<Question> getQuestionById(UUID id) {
        logger.info("In the question service method");
              return  questionRepository.getQuestionById(id);
    }
}
