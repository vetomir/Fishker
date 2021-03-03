package xyz.szczerba.languageapp.question;

import lombok.Getter;

@Getter
public enum Level {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5);

    private int val;

    Level(int val) {
        this.val = val;
    }
}
