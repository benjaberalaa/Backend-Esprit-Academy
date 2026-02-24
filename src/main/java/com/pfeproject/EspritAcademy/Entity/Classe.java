package com.pfeproject.EspritAcademy.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Classe")
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    @JsonManagedReference("user-classe")
    private List<User> users;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    @JsonManagedReference("course-classe")
    private List<CoursEntity> courses;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    @JsonManagedReference("subject-classe")
    private List<Subject> subjects;
}
