package com.ps.helper;

import com.ps.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionHelper {
    public static Session sessionCreation() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Quiz.class)
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(AnswerChoices.class)
                .addAnnotatedClass(Subject.class)
                .addAnnotatedClass(Difficulty.class)
                .buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }
}
