package xyz.szczerba.languageapp.question.query;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "questions")
public class QuestionQuery {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
}
