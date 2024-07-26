package com.rs.employer.dto;

import com.rs.employer.ValidateAnotation.ValidateRole;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
// @NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class AuthenticationDto {
    @NotNull
    @Id
    public String username;
    @NotNull
    public String password;
    @ValidateRole
    public String role;

    public AuthenticationDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AuthenticationDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
