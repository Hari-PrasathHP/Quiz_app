package com.ps.repository;


import com.ps.domain.*;
import com.ps.helper.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.UUID;


public class QuizRepositoryImpl implements QuizRepository {
    private static final Logger logger = LogManager.getLogger(QuizRepositoryImpl.class);

    @Override
    public Quiz createQuiz(Quiz quiz) {
        try (Session session = SessionHelper.sessionCreation()) {
            session.beginTransaction();
            session.save(quiz);
            session.getTransaction().commit();

        }
        Quiz quiz1;
        try (Session session = SessionHelper.sessionCreation()) {
            session.beginTransaction();
            Query query = session.createQuery("from Quiz where id='" + quiz.getId() + "'");
            quiz1 = (Quiz) query.getSingleResult();
            session.getTransaction().commit();

        }
        return quiz1;
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        try (Session session = SessionHelper.sessionCreation()) {
            session.beginTransaction();
            Quiz quiz1 = session.get(Quiz.class, quiz.getId());
            List<Question> questionList = quiz.getQuestions();
            for (Question question : questionList) {
                question.setQuiz(quiz1);
                List<AnswerChoices> answerChoicesList = question.getAnswerChoices();
                for (AnswerChoices answerChoices : answerChoicesList) {
                    answerChoices.setQuestion(question);
                }
                question.setAnswerChoices(answerChoicesList);
            }
            quiz1.setQuestions(questionList);
            session.save(quiz1);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteQuiz(UUID id) {
        try (Session session = SessionHelper.sessionCreation()) {
            session.beginTransaction();
            Quiz quiz = session.get(Quiz.class, id);
            session.delete(quiz);
            session.getTransaction().commit();

        } catch (Exception e) {
            logger.error(e);
            logger.catching(e);
            throw new RuntimeException("Error deleting quiz");
        }
    }

    @Override
    public List<Quiz> getQuizBySubject(String subject, String difficulty) {
        try (Session session = SessionHelper.sessionCreation()) {
            session.beginTransaction();
            Query query = session.createQuery("from Quiz where subject ='" + subject + "'" + "and difficulty ='" + difficulty + "'");
            List<Quiz> quizList = query.getResultList();
            session.getTransaction();
            return quizList;
        }
    }
}

