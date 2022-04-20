package com.ps.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ps.domain.Question;
import com.ps.domain.Quiz;
import com.ps.service.QuestionService;
import com.ps.service.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = {"/question/answer"})
public class QuestionController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(QuestionController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        UUID id = UUID.fromString((req.getParameter("id")));
        logger.info("got request from the client for retrieving list of questions by quiz id:" + id);
        QuestionService questionService = new QuestionService();
        try {
            setAccessControlHeaders(resp);
            List<Question> questionList = questionService.getQuestionById(id);
            logger.info(questionList);
            Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String usersToSting = gson.toJson(questionList);
            logger.info("the json returning is:"+usersToSting);
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
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-origin","http://localhost:9000");
        response.setHeader("Access-Control-Allow-Methods","GET");
    }


}
