package com.pfeproject.EspritAcademy.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"student_id","quiz_id"}
        ), name = "quiz_score"
)
public class QuizScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private Integer score;
    private Integer total;
    private Long durationSec;
    private Boolean finished;
}

