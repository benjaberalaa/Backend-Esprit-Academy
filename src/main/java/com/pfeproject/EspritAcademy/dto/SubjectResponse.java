package com.pfeproject.EspritAcademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponse {
    private Long id;
    private String title;
    private Long classeId;
    private List<CourseResponse> courses;
}
