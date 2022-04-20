package com.ps.controller;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.ps.service.SubmitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/submit"})
public class SubmitController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(com.ps.controller.SubmitController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
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
//            PrintWriter writer1 = resp.getWriter();
//            writer1.write(String.valueOf(ids));
//            writer1.flush();
            SubmitService submitService = new SubmitService();
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

