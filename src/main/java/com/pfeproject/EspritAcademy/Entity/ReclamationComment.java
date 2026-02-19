package com.pfeproject.EspritAcademy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReclamationComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String text;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "reclamation_id")
    @JsonIgnore
    private reclamation reclamation;

}
