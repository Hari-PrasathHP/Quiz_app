package com.ps.quizapp.exception;

import com.ps.quizapp.controller.QuizController;
import com.ps.quizapp.domain.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

   @ExceptionHandler
    public ResponseEntity<WebResponse> handleNotFoundException(NotFoundException nfe){
       logger.error(nfe.getMessage());
        WebResponse webResponse = new WebResponse<>(false,nfe.getMessage(),new Date());
        ResponseEntity<WebResponse> response = new ResponseEntity<>(webResponse, HttpStatus.NOT_FOUND);
        return response;
     }

    @ExceptionHandler
     public ResponseEntity<WebResponse> Exception(Exception e){
       logger.error(e.getMessage());
           WebResponse webResponse = new WebResponse<>(false,e.getMessage(),new Date());
           ResponseEntity<WebResponse> response = new ResponseEntity<>(webResponse,HttpStatus.BAD_REQUEST);
           return response;
     }
}
