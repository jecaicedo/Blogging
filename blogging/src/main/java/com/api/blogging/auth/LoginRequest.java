package com.api.blogging.auth;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String username;
    String password;
}
