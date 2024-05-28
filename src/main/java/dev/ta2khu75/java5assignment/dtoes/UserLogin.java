package dev.ta2khu75.java5assignment.dtoes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @Email @NotBlank
    String email;
    @NotBlank
    String password;    
    boolean rememberMe=false;
}
