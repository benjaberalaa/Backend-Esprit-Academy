package com.pfeproject.EspritAcademy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPassword {
    private String token;
    private String password;
}
