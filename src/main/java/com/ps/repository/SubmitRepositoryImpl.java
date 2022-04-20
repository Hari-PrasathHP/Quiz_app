package com.ps.repository;

import com.ps.domain.AnswerChoices;
import com.ps.helper.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class SubmitRepositoryImpl implements  SubmitRepository {
    private static final Logger logger = LogManager.getLogger(SubmitRepositoryImpl.class);

    @Override
    public int getMarks(List<String> ids) {
        int mark=0;
        try (Session session = SessionHelper.sessionCreation()) {
            session.beginTransaction();
            for (String i : ids) {
               boolean value = false;
                Query query = session.createQuery("select isRightAnswer from AnswerChoices where id='" + i+"'");
                value = (boolean) query.getSingleResult();
                logger.info(value);
                if (value) {
                    mark += 1;
                }
            }
            session.getTransaction();
        }
        return mark;
    }
}

