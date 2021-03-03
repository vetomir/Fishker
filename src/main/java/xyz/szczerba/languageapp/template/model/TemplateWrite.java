package xyz.szczerba.languageapp.template.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
class TemplateWrite {
    @NotBlank(message = "Question is required")
    private String question;
    @NotBlank(message = "Answer is required")
    private String answer;

    @NotNull(message = "Topic's 'id' is required")
    private long topicId;
}
