package com.ps.quizapp.controller;

import com.ps.quizapp.domain.AnswerChoices;
import com.ps.quizapp.domain.Question;
import com.ps.quizapp.domain.Quiz;
import com.ps.quizapp.domain.WebResponse;
import com.ps.quizapp.service.QuestionService;
import com.ps.quizapp.service.QuizService;
import com.ps.quizapp.service.SubmitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    private final SubmitService submitService;
    private final QuizService quizService;
    private final QuestionService questionService;


    @Autowired
    public QuizController(SubmitService submitService, QuizService quizService, QuestionService questionService) {
        this.submitService = submitService;
        this.quizService = quizService;
        this.questionService = questionService;
    }


    @PostMapping
    public WebResponse<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz quiz1 = null;
        if ((String.valueOf(quiz.getSubject()).equals("MATHS") || String.valueOf(quiz.getSubject()).equals("CHEMISTRY")
                || String.valueOf(quiz.getSubject()).equals("BIOLOGY")
                || String.valueOf(quiz.getSubject()).equals("PHYSICS")) && (String.valueOf(quiz.getDifficulty()).equals("BEGINNER")
                || String.valueOf(quiz.getDifficulty()).equals("INTERMEDIATE") || String.valueOf(quiz.getDifficulty()).equals("ADVANCED"))) {
            quiz1 = quizService.createQuiz(quiz);
            quiz1.setQuestions(null);
        }
        WebResponse<Quiz> webResponse = new WebResponse<>(true, "quiz created successfully", quiz1, new Date());
        return webResponse;
    }


    @PutMapping
    protected WebResponse<Quiz> quizUpdation(@RequestBody Quiz quiz) {
        Quiz quiz1 = quizService.updateQuiz(quiz);
        for (int i = 0; i < quiz1.getQuestions().size(); i++) {
            quiz1.getQuestions().get(i).setQuiz(null);
            for (int j = 0; j < quiz1.getQuestions().get(i).getAnswerChoices().size(); j++) {
                quiz1.getQuestions().get(i).getAnswerChoices().get(j).setQuestion(null);
            }
        }
        logger.info("updated quiz successfully." + quiz1);
        WebResponse<Quiz>  webResponse = new WebResponse<>(true, "quiz updated successfully", quiz1, new Date());
        return webResponse;

    }

    @RequestMapping("/list")
    public WebResponse<List<Quiz>> getQuizList(@RequestParam("subject") String subject, @RequestParam("difficulty") String difficulty) {
        List<Quiz> quizList = quizService.getQuizBySubject(subject, difficulty);
        for (Quiz quiz : quizList) {
            quiz.setQuestions(null);
        }
        WebResponse<List<Quiz>> webResponse = new WebResponse<>(true, "quiz retrieval successful", quizList, new Date());
        return webResponse;
    }


    @DeleteMapping("/{id}")
    protected WebResponse quizDeletion(@PathVariable UUID id) {
        quizService.deleteQuiz(id);
        WebResponse webResponse = new WebResponse<>(true, "Deletion successful", new Date());
        return webResponse;
    }

    @GetMapping("/question")
    public WebResponse<List<Question>> getQuestionAnswerById(@RequestParam("id") UUID id) {
        logger.info("got request from the client for retrieving list of questions by quiz id:" + id);
        List<Question> questionList = questionService.getQuestionById(id);
        for (Question question : questionList) {
            question.setQuiz(null);
            for (AnswerChoices answerChoices : question.getAnswerChoices()) {
                answerChoices.setQuestion(null);
            }
        }
        logger.info("updated quiz successfully." + questionList);
        WebResponse<List<Question>>  webResponse = new WebResponse<>(true, "question fetched successfully", questionList, new Date());
        return webResponse;
    }

    @PostMapping("/mark")
    protected WebResponse<Integer> getMark(@RequestBody List<String> ids) {
        logger.info("inside do post submit controller");
        logger.info(String.valueOf(ids));
        int mark = submitService.getMark(ids);
      WebResponse<Integer>  webResponse = new WebResponse<>(true, "The mark has been return", mark, new Date());
        return webResponse;
    }

    @DeleteMapping("/question/{id}")
    protected WebResponse questionDeletion(@PathVariable UUID id) {
        questionService.deleteQuestion(id);
        WebResponse webResponse = new WebResponse<>(true, "Deletion successful", new Date());
        return webResponse;
    }
}





