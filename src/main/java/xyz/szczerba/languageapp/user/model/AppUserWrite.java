package xyz.szczerba.languageapp.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
class AppUserWrite {
    @NotBlank(message = "Nickname is required")
    private String nickname;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Password Repeat is required")
    private String passwordRepeat;
}
