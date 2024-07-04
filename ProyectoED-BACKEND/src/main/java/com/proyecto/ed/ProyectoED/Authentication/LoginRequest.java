package com.proyecto.ed.ProyectoED.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    String email;
    String password;
    String captchaValue;
    private String captchaToken;


}
