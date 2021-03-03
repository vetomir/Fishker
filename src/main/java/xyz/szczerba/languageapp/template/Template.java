package xyz.szczerba.languageapp.template;

import lombok.Getter;
import lombok.Setter;
import xyz.szczerba.languageapp.question.query.QuestionQuery;
import xyz.szczerba.languageapp.topic.query.TopicQuery;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "templates")
class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;
    @NotBlank
    private String answer;
    @NotBlank
    private String type;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id",  insertable = false)
    private Set<QuestionQuery> questions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", updatable = false)
    private TopicQuery topic;

    void update(String name, String answer, String type){
        this.name = name;
        this.answer = answer;
        this.type = type;
    }
}
