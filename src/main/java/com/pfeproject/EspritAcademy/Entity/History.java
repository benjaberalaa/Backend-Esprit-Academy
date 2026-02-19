package com.pfeproject.EspritAcademy.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class History {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String themeName;
    private String levelName;
    private int total;
    private int score;
}
