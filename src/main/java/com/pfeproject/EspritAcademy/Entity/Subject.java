package com.pfeproject.EspritAcademy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonManagedReference("subject-course")
    private List<CoursEntity> courses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id")
    @JsonBackReference("subject-classe")
    private Classe classe;
}
