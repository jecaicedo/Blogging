package com.api.blogging.auth;

import com.api.blogging.exception.InvalidLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {
        if (request.getLogin() == null || request.getLogin().trim().isEmpty()) {
            ErrorResponse error = ErrorResponse.builder().errorMessage("Email/Username is required").build();
            return ResponseEntity.badRequest().body(error);
        }

        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            ErrorResponse error = ErrorResponse.builder().errorMessage("Password is required").build();
            return ResponseEntity.badRequest().body(error);
        }

        try {
            AuthResponse authResponse = authService.login(request);
            return ResponseEntity.ok(authResponse);
        } catch (InvalidLoginException ex) {
            ErrorResponse error = ErrorResponse.builder().errorMessage(ex.getMessage()).build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        } catch (Exception ex) {
            ErrorResponse error = ErrorResponse.builder().errorMessage("An error occurred").build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}