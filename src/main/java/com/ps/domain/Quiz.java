package com.ps.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    private List<Question> questions = new ArrayList<>();

    @Column(name = "no_of_question")
    private int noOfQuestion;

    @Column(name = "duration")
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject")
    private Subject subject;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    public Quiz() {

    }

    public Quiz(int noOfQuestion, int duration, Subject subject, String title,Difficulty difficulty) {

        this.noOfQuestion = noOfQuestion;
        this.duration = duration;
        this.subject = subject;
        this.title = title;
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(int noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", questions=" + questions +
                ", noOfQuestion=" + noOfQuestion +
                ", duration=" + duration +
                ", subject=" + subject +
                ", title='" + title + '\'' +
                '}';
    }
}
