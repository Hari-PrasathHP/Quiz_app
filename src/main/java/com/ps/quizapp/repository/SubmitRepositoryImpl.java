package com.ps.quizapp.repository;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SubmitRepositoryImpl implements  SubmitRepository {
    private static final Logger logger = LoggerFactory.getLogger(SubmitRepositoryImpl.class);

 private final EntityManager entityManager;

 @Autowired
    public SubmitRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public int getMarks(List<String> ids) {
        int mark=0;
        try {
            for (String i : ids) {
               boolean value = false;
                Query query = entityManager.createQuery("select isRightAnswer from AnswerChoices where id='" + i+"'");
                value = (boolean) query.getSingleResult();
                if (value) {
                    mark += 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mark;
    }
}

