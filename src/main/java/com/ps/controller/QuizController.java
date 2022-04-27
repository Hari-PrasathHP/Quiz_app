package com.ps.controller;

import com.google.gson.Gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.ps.domain.Question;
import com.ps.domain.Quiz;
import com.ps.service.QuestionService;
import com.ps.service.QuizService;
import com.ps.service.SubmitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/quiz")
public class QuizController  {
    private static final Logger logger = LogManager.getLogger(com.ps.controller.QuizController.class);

    @Autowired
    private SubmitService submitService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;


    @PostMapping
    protected void createQuiz(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("it is in do post quiz creation method");
        try (JsonReader jsonReader = new JsonReader(req.getReader())) {

            Quiz quiz = new Gson().fromJson(jsonReader, Quiz.class);
            logger.info("got the quiz input fields from client for creating a quiz" + quiz);
            if ((String.valueOf(quiz.getSubject()).equals("MATHS") || String.valueOf(quiz.getSubject()).equals("CHEMISTRY")
                    || String.valueOf(quiz.getSubject()).equals("BIOLOGY")
                    || String.valueOf(quiz.getSubject()).equals("PHYSICS")) && (String.valueOf(quiz.getDifficulty()).equals("BEGINNER")
                    || String.valueOf(quiz.getDifficulty()).equals("INTERMEDIATE") || String.valueOf(quiz.getDifficulty()).equals("ADVANCED"))) {
                Quiz quiz1 = quizService.createQuiz(quiz);
                quiz1.setQuestions(null);
                String userToString = new Gson().toJson(quiz1);
                resp.setContentType("application/json");
                PrintWriter writer = resp.getWriter();
                writer.write(userToString);
                writer.flush();
                resp.setStatus(200);
                writer.flush();
            } else {
                logger.info("quiz: " + quiz);
                PrintWriter writer = resp.getWriter();
                writer.write("Quiz creation unsuccessful");
                resp.setStatus(200);
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);
            PrintWriter writer = resp.getWriter();
            writer.write("Quiz not created");
            writer.flush();

        }
    }


@PutMapping
    protected void quizUpdation(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("getting into the do put method" + req);
        try (JsonReader jsonReader = new JsonReader(req.getReader())) {
            Quiz quiz = new Gson().fromJson(jsonReader, Quiz.class);

            quizService.updateQuiz(quiz);
            resp.setStatus(200);
            logger.info(quiz.getQuestions());
            logger.info("quiz: " + quiz);
            PrintWriter writer = resp.getWriter();
            writer.write("Question and answer updated successfully");
            resp.setStatus(200);
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);
        }

    }

    @RequestMapping("/list")
    public String getQuizList( @RequestParam("subject") String subject, @RequestParam("difficulty") String difficulty, Model model) {
        logger.info("got request from the client for retrieving list of quiz  :" + subject+" "+difficulty);

        try {
            List<Quiz> quizList = quizService.getQuizBySubject(subject, difficulty);
            for (Quiz quiz : quizList) {
                quiz.setQuestions(null);
            }
            model.addAttribute("quizList",quizList);
           return "quiz";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);
        }return null;
    }

    @DeleteMapping
    protected void quizDeletion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (InputStream inputStream = req.getInputStream()) {
            try (javax.json.JsonReader jsonReader = Json.createReader(inputStream)) {
                JsonObject jsonObject = jsonReader.readObject();
                UUID id = UUID.fromString(jsonObject.getString("id"));
                quizService.deleteQuiz(id);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e);
                logger.catching(e);
                PrintWriter writer = resp.getWriter();
                writer.write("please check the data you have entered");
                writer.flush();
            }
        }
    }

    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-origin","http://localhost:9000");
        response.setHeader("Access-Control-Allow-Methods","GET");
    }


    @GetMapping("/question")
    public String getQuestionAnswerById(@RequestParam("id") UUID id,Model model) {
        logger.info("got request from the client for retrieving list of questions by quiz id:" + id);

        try {
            List<Question> questionList = questionService.getQuestionById(id);
            logger.info(questionList);


            model.addAttribute("questionList",questionList);
            return "question";

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);
        }
        return null;
    }

    @PostMapping("/mark")
    protected void getMark(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("inside do post submit controller");
        try (JsonReader jsonReader = new JsonReader(req.getReader())) {
            List<String> ids = new ArrayList<>();
            jsonReader.beginObject();
            String name = jsonReader.nextName();
            if (jsonReader.peek() != JsonToken.NULL) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    logger.info("it is getting added to the list");
                    ids.add(jsonReader.nextString());
                }
                jsonReader.endArray();
                logger.info("got the answer id from client to calculate the mark" + ids);
            }
            jsonReader.endObject();

            int mark = submitService.getMark(ids);
            logger.info(mark);
            String usersToSting = new Gson().toJson(mark);
            setAccessControlHeaders(resp);
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            writer.write(usersToSting);
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);
        }
    }

    @RequestMapping(value = "/createQuiz")
    public String registeringStudent(Model model){
        model.addAttribute("quiz",new Quiz());
        return "createForm";
    }
}



