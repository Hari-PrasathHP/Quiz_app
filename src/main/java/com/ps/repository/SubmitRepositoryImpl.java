package com.ps.repository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class SubmitRepositoryImpl implements  SubmitRepository {
    private static final Logger logger = LogManager.getLogger(SubmitRepositoryImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int getMarks(List<String> ids) {
        int mark=0;
        try {
           Session session = sessionFactory.getCurrentSession();
            for (String i : ids) {
               boolean value = false;
                Query query = session.createQuery("select isRightAnswer from AnswerChoices where id='" + i+"'");
                value = (boolean) query.getSingleResult();
                logger.info(value);
                if (value) {
                    mark += 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.catching(e);
            logger.error(e);
        }
        return mark;
    }
}

