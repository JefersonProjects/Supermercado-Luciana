package com.proyecto.ed.ProyectoED.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class CaptchaService {
    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verifyCaptcha(String captchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> body = new HashMap<>();
        body.put("secret", recaptchaSecret);
        body.put("response", captchaResponse);

        ResponseEntity<Map> response = restTemplate.postForEntity(RECAPTCHA_VERIFY_URL, body, Map.class);
        Map<String, Object> responseBody = response.getBody();

        return (Boolean) responseBody.get("success");
    }
}
