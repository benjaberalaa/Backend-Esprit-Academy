package com.pfeproject.EspritAcademy.Entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question_option")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private Boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}

