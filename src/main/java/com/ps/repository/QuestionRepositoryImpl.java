package com.ps.repository;

import com.ps.domain.Question;
import com.ps.helper.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private static final Logger logger = LogManager.getLogger(QuestionRepositoryImpl.class);

@Autowired
private SessionFactory sessionFactory;

    @Override
    public List<Question> getQuestionById(UUID id) {
        try  {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Question as q where quiz='" + id + "'");
            List<Question> questionList = query.getResultList();
            for (Question question:questionList) {
                question.getAnswerChoices();
            }
            return questionList;
        }catch (Exception e ){
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);

        }
return  null;
    }
}
