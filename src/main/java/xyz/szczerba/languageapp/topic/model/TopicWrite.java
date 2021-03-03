package xyz.szczerba.languageapp.topic.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
class TopicWrite {
    @NotBlank(message = "Topic 'name' cannot be blank")
    private String name;
}
