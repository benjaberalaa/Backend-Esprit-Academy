package com.pfeproject.EspritAcademy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Level {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;
    private String difficulty;

    @JsonIgnore
    @OneToMany(mappedBy = "level", cascade  = CascadeType.ALL)
    private List<Question> questions;

    @JsonIgnore
    @ManyToOne
    private Theme theme;

    public void addQuestion(Question  question) {
        if(getQuestions()==null) {
            this.questions = new ArrayList<>();
        }
        getQuestions().add( question);
        question.setLevel(this);
    }
}
