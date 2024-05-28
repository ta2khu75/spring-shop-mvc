package dev.ta2khu75.java5assignment.resps;

import java.io.Serializable;

import dev.ta2khu75.java5assignment.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResp implements Serializable {
    Long id;
    String name;
    String email;
    boolean locked;
    Role role;
}
