package com.pfeproject.EspritAcademy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {
    private Long id;

    private String label;

    private Boolean correct;

    private Long questionId;
}
