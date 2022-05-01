package com.ps.quizapp.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "answer_choices")
public class AnswerChoices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "is_right_answer")
    private boolean isRightAnswer;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;


    public AnswerChoices(String title, boolean isRightAnswer, Question question) {
        this.title = title;
        this.isRightAnswer = isRightAnswer;
        this.question = question;
    }

    public AnswerChoices() {

    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsRightAnswer() {
        return isRightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        isRightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return "AnswerChoices{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isRightAnswer=" + isRightAnswer +
                '}';
    }
}
