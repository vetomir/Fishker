package xyz.szczerba.languageapp.topic;

import lombok.Getter;
import lombok.Setter;
import xyz.szczerba.languageapp.template.query.TemplateQuery;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "topics")
class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id",  insertable = false)
    private Set<TemplateQuery> templates = new HashSet<>();

    void update(String title){
        this.title = title;
    }
}
