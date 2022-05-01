package com.ps.quizapp.repository;


import com.ps.quizapp.domain.Question;
import com.ps.quizapp.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private static final Logger logger = LoggerFactory.getLogger(QuestionRepositoryImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public QuestionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Question> getQuestionById(UUID id) {
        List<Question> questionList = null;
        Query query = entityManager.createQuery("from Question as q where quiz='" + id + "'");
        questionList = query.getResultList();
        if (questionList == null) {
            throw new NotFoundException("Invalid id for fetching questions"+id);
        }
        for (Question question : questionList) {
            question.getAnswerChoices();
        }
        return questionList;
    }


    public void deleteQuestion(UUID id) {
        Question question = entityManager.find(Question.class, id);
        if (question == null) {
            throw new NotFoundException("Given id is not found" + id);
        }
        entityManager.remove(question);
    }
}
