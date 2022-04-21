package com.ps.repository;


import com.ps.domain.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Repository
public class QuizRepositoryImpl implements QuizRepository {
    private static final Logger logger = LogManager.getLogger(QuizRepositoryImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    public Quiz createQuiz(Quiz quiz) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(quiz);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);
            throw new RuntimeException("Error creating a Quiz");
        }
        Quiz quiz1=null;
        try  {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Quiz where id='" + quiz.getId() + "'");
            quiz1 = (Quiz) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return quiz1;
    }


    public void updateQuiz(Quiz quiz) {
        try  {
            Session session = sessionFactory.getCurrentSession();
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

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);
        }
    }


    public void deleteQuiz(UUID id) {
        try  {
            Session session = sessionFactory.getCurrentSession();
            Quiz quiz = session.get(Quiz.class, id);
            session.delete(quiz);

        } catch (Exception e) {
            logger.error(e);
            logger.catching(e);
            throw new RuntimeException("Error deleting quiz");
        }
    }


    public List<Quiz> getQuizBySubject(String subject, String difficulty) {
        try  {
            logger.info("In the getQUizBySubject impl method"+subject+""+difficulty);
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Quiz where subject ='" + subject + "'" + "and difficulty ='" + difficulty + "'");
            List<Quiz> quizList = query.getResultList();
            logger.info(quizList);
            return quizList;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);
            throw new RuntimeException("Error Getting list of Quiz");
        }
    }
}

