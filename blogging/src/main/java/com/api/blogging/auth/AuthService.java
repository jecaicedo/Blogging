package com.api.blogging.auth;

import com.api.blogging.exception.InvalidLoginException;
import com.api.blogging.jwt.JwtService;
import com.api.blogging.repositories.IUserRepository;
import com.api.blogging.models.UserModel;
import com.api.blogging.models.UserRoleModel;
import com.api.blogging.repositories.IRoleRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IRoleRepository roleRepository;

    public AuthResponse login(LoginRequest request) {
        String login = request.getLogin().trim();
        String password = request.getPassword().trim();

        if (login == null || login.isEmpty()) {
            throw new InvalidLoginException("Email/Username is required");
        }
        if (password == null || password.isEmpty()) {
            throw new InvalidLoginException("Password is required");
        }

        UserModel user;

        if (isEmail(login)) {
            user = userRepository.findByEmail(login.toLowerCase())
                    .orElseThrow(() -> new InvalidLoginException("No user found"));
        } else {
            user = userRepository.findByUsername(login)
                    .orElseThrow(() -> new InvalidLoginException("Invalid email or username"));
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidLoginException("Invalid password");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {

        if (isEmail(request.getEmail())){

            Optional<UserRoleModel> roleOptional = roleRepository.findById((long)1);
            UserRoleModel role = roleOptional.orElseThrow(() -> new RuntimeException("Role not found"));

            UserModel user = UserModel.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode( request.getPassword()))
                    .role(role)
                    .build();

            userRepository.save(user);

            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
        } else {
            throw new InvalidLoginException("Email/Username is onvalid");
        }

    }

    private boolean isEmail(String login) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return login != null && login.matches(emailRegex);
    }

}
