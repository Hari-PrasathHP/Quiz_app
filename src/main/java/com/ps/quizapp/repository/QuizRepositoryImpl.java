package com.ps.quizapp.repository;

import com.ps.quizapp.controller.QuizController;
import com.ps.quizapp.domain.AnswerChoices;
import com.ps.quizapp.domain.Question;
import com.ps.quizapp.domain.Quiz;
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
public class QuizRepositoryImpl implements QuizRepository {
    private static final Logger logger = LoggerFactory.getLogger(QuizRepositoryImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public QuizRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Quiz createQuiz(Quiz quiz) {
        entityManager.persist(quiz);
        return quiz;
    }

    public Quiz updateQuiz(Quiz quiz) {
        Quiz quiz1 = entityManager.find(Quiz.class, quiz.getId());
        if (quiz1 == null) {
            throw new NotFoundException("Quiz id is invalid");
        }
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
        entityManager.persist(quiz1);
        return quiz1;
    }


    public void deleteQuiz(UUID id) {
        Quiz quiz = entityManager.find(Quiz.class, id);
        if (quiz == null) {
            throw new NotFoundException("Given id is not found" + id);
        }
        entityManager.remove(quiz);
    }


    public List<Quiz> getQuizBySubject(String subject, String difficulty) {
        List<Quiz> quizList = null;
        logger.info("In the getQUizBySubject impl method" + subject + "" + difficulty);
        Query query = entityManager.createQuery("from Quiz where subject ='" + subject + "'" + "and difficulty ='" + difficulty + "'");
        quizList = query.getResultList();
        if (quizList == null) {
            throw new NotFoundException("No quiz list is found matching subject and difficulty" + subject + " " + difficulty);
        }
        logger.info(String.valueOf(quizList));
        return quizList;
    }
}

