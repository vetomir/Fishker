package xyz.szczerba.languageapp.user;


import lombok.Getter;

@Getter
enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String name;

    Role(String name) { this.name = name; }
}
