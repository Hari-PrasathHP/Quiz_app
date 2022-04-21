package com.ps.controller;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ps.domain.Quiz;
import com.ps.service.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;


@WebServlet(urlPatterns = {"/quiz"})
public class QuizController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(com.ps.controller.QuizController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("it is in do post quiz creation method");
        try (JsonReader jsonReader = new JsonReader(req.getReader())) {

            Quiz quiz = new Gson().fromJson(jsonReader, Quiz.class);
            logger.info("got the quiz input fields from client for creating a quiz" + quiz);
            if ((String.valueOf(quiz.getSubject()).equals("MATHS") || String.valueOf(quiz.getSubject()).equals("CHEMISTRY")
                    || String.valueOf(quiz.getSubject()).equals("BIOLOGY")
                    || String.valueOf(quiz.getSubject()).equals("PHYSICS")) && (String.valueOf(quiz.getDifficulty()).equals("BEGINNER")
                    || String.valueOf(quiz.getDifficulty()).equals("INTERMEDIATE") || String.valueOf(quiz.getDifficulty()).equals("ADVANCED"))) {
                QuizService quizService = new QuizService();
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


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("getting into the do put method" + req);
        try (JsonReader jsonReader = new JsonReader(req.getReader())) {
            Quiz quiz = new Gson().fromJson(jsonReader, Quiz.class);
            QuizService quizService = new QuizService();
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String subject = (req.getParameter("subject"));
        String difficulty = (req.getParameter("difficulty"));
        logger.info("got request from the client for retrieving list of questions by quiz id:" + subject);
        QuizService quizService = new QuizService();
        try {
            List<Quiz> quizList = quizService.getQuizBySubject(subject, difficulty);
            for (Quiz quiz : quizList) {
                quiz.setQuestions(null);
            }

            String usersToSting = new Gson().toJson(quizList);
            setAccessControlHeaders(resp);
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            writer.write(usersToSting);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
            logger.catching(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (InputStream inputStream = req.getInputStream()) {
            try (javax.json.JsonReader jsonReader = Json.createReader(inputStream)) {
                JsonObject jsonObject = jsonReader.readObject();
                UUID id = UUID.fromString(jsonObject.getString("id"));
                QuizService quizService = new QuizService();
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

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-origin","http://localhost:9000");
        response.setHeader("Access-Control-Allow-Methods","GET");
    }

}



