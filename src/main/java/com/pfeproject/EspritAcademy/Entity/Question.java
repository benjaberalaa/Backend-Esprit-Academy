package com.pfeproject.EspritAcademy.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private Integer points;
    private Integer timer; // In seconds

    @Enumerated(EnumType.STRING)
    private com.pfeproject.EspritAcademy.Entity.Enum.QuestionType type;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options;

    @ManyToOne
    private Level level;
}
