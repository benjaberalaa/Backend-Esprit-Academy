package com.pfeproject.EspritAcademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseResponse {
    private Long id;
    private String name;
    private List<UserDto> students;
    private List<UserDto> teachers;
    private List<String> subjects;
    private int studentCount;
    private int teacherCount;
}
