package com.pfeproject.EspritAcademy.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private Integer level;
    private Boolean active;

    // New Fields
    private Integer duration; // In minutes
    private Integer minScore; // Minimum score to pass
    private String category;

    private java.time.LocalDateTime startDate;
    private java.time.LocalDateTime endDate;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;
}