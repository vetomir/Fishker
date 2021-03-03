package xyz.szczerba.languageapp.topic.query;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "topics")
public
class TopicQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
