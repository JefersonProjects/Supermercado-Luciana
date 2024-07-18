package com.proyecto.ed.ProyectoED.Controllers;

import com.proyecto.ed.ProyectoED.Authentication.AuthResponse;
import com.proyecto.ed.ProyectoED.Authentication.JwtUtil;
import com.proyecto.ed.ProyectoED.Authentication.LoginRequest;
import com.proyecto.ed.ProyectoED.Authentication.RegisterRequest;
import com.proyecto.ed.ProyectoED.Dao.AdministradroImpl;
import com.proyecto.ed.ProyectoED.Dao.ClienteImpl;
import com.proyecto.ed.ProyectoED.Models.Cliente;
import com.proyecto.ed.ProyectoED.Models.Usuario;
import com.proyecto.ed.ProyectoED.Services.AuthService;
import com.proyecto.ed.ProyectoED.Services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private JwtUtil jwtUtil;
    private AdministradroImpl administradorRepository;
    private ClienteImpl clienteRepository;

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;
    private final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            if (!verifyCaptcha(request.getCaptchaToken())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse(null, null, null, null, null, null, null));
            }

            AuthResponse response = authService.login(request.getEmail(), request.getPassword());
            System.out.println("rol enviado al front :" + response.getRole());
            System.out.println("nombre:" + response.getNombre());
            System.out.println("dni:" + response.getDni());
            System.out.println("email:" + response.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, null, null, null, null, null, null));
        }
    }

    private boolean verifyCaptcha(String captchaToken) {
        RestTemplate restTemplate = new RestTemplate();
        String params = "?secret=" + recaptchaSecret + "&response=" + captchaToken;
        RecaptchaResponse recaptchaResponse = restTemplate.postForObject(RECAPTCHA_VERIFY_URL + params, null, RecaptchaResponse.class);
        return recaptchaResponse.isSuccess();
    }

    private static class RecaptchaResponse {
        private boolean success;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
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

    @GetMapping("/me")
    public ResponseEntity<Usuario> getCurrentUser(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        Usuario usuario = administradorRepository.findByEmail(email);
        if (usuario == null) {
            usuario = clienteRepository.findByEmail(email);
        }
        return ResponseEntity.ok(usuario);
    }

}
