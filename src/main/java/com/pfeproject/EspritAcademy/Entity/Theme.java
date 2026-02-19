package com.pfeproject.EspritAcademy.Entity;

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
public class Theme {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy  =  "theme",cascade  = CascadeType.ALL)
    private List<Level> levels;

    public void addLevel(Level  level) {
        if(getLevels()==null) {
            this.levels = new ArrayList<>();
        }
        getLevels().add( level);
        level.setTheme(this);
    }
}
