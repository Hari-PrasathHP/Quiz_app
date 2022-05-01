package com.ps.quizapp.domain;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;


    @Column(name = "description")
    private String description;



    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<AnswerChoices> answerChoices = new ArrayList<>();

    public Question() {

    }

    public Question(String description, Quiz quiz) {
        this.description = description;
        this.quiz = quiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AnswerChoices> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<AnswerChoices> answerChoices) {
        this.answerChoices = answerChoices;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", answerchoices= "+answerChoices+
                '}';
    }
}
