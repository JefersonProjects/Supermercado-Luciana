package com.proyecto.ed.ProyectoED.Controllers;

import com.proyecto.ed.ProyectoED.Authentication.AuthResponse;
import com.proyecto.ed.ProyectoED.Authentication.LoginRequest;
import com.proyecto.ed.ProyectoED.Authentication.RegisterRequest;
import com.proyecto.ed.ProyectoED.Services.AuthService;
import com.proyecto.ed.ProyectoED.Services.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request.getEmail(), request.getPassword());
            System.out.println("rol enviado al front :" + response.getRole());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.registerCliente(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse(null, null));
        }
    }
}
