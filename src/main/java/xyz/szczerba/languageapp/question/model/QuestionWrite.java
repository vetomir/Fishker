package xyz.szczerba.languageapp.question.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
class QuestionWrite {
    @NotNull(message = "Template's 'id' is required")
    long templateId;
    long userId;
}
