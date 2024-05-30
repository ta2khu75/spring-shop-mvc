package dev.ta2khu75.java5assignment.resps;

import java.io.Serializable;

import dev.ta2khu75.java5assignment.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResp implements Serializable {
    @NotNull
    Long id;
    @NotBlank
    String name;
    @Email @NotBlank
    String email;
    boolean locked;
    Role role;
}
