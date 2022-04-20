//package com.ps.domain;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public class InsertDemo {
//    public static void main(String[] args) {
//        Session session = null;
//        try {
//            SessionFactory sessionFactory = new Configuration()
//                    .configure("hibernate.cfg.xml")
//                    .addAnnotatedClass(Quiz.class)
//                    .addAnnotatedClass(Question.class)
//                    .addAnnotatedClass(AnswerChoices.class)
//                    .addAnnotatedClass(Subject.class)
//                    .buildSessionFactory();
//            session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//            Quiz quiz = new Quiz(2,30,Subject.MATHS,"Basics of maths",Difficulty.BEGINNER);
//            Question question1 = new Question("what is 4+4",Subject.MATHS,quiz);
//            Question question2 = new Question("which number comes after 20",Subject.MATHS,quiz);
//            AnswerChoices answerChoices = new AnswerChoices("6",false,question1);
//            AnswerChoices answerChoices1 = new AnswerChoices("8",true,question1);
//            AnswerChoices answerChoices2 = new AnswerChoices("4",false,question1);
//            AnswerChoices answerChoices4 = new AnswerChoices("21",true,question2);
//            AnswerChoices answerChoices5 = new AnswerChoices("22",false,question2);
//            AnswerChoices answerChoices6 = new AnswerChoices("8",false,question2);
//            question1.getAnswerChoices().add(answerChoices);
//            question1.getAnswerChoices().add(answerChoices1);
//            question1.getAnswerChoices().add(answerChoices2);
//            question2.getAnswerChoices().add(answerChoices4);
//            question2.getAnswerChoices().add(answerChoices5);
//            question2.getAnswerChoices().add(answerChoices6);
//            quiz.getQuestions().add(question1);
//            quiz.getQuestions().add(question2);
//            session.save(quiz);
//
//
//
//            session.getTransaction().commit();
//
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
//
//}
