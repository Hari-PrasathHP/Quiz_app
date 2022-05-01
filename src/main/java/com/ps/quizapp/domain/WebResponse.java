package com.ps.quizapp.domain;


import java.util.Date;

public class WebResponse<T> {
    private boolean ok;
    private String message;
    private T object;
    private Date date;


    public WebResponse(boolean ok, String message, Date date) {
        this.ok=ok;
        this.message = message;
        this.date = date;
    }


    public WebResponse(boolean ok, String message, T object, Date date) {
        this.ok = ok;
        this.message = message;
        this.object = object;
        this.date = date;
    }


    public boolean isOk() {
        return ok;
    }


    public void setOk(boolean ok) {
        this.ok = ok;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public T getObject() {
        return object;
    }


    public void setObject(T object) {
        this.object = object;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
