package com.pfeproject.EspritAcademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This DTO is used to return course info WITHOUT the file bytes
// We use this for listing courses so we don't send huge data
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    // The ID of the course/file in the database
    private Long id;

    // The name of the file (like "math_lesson.pdf")
    private String fileName;

    // The ID of the subject this course belongs to
    private Long subjectId;

}
