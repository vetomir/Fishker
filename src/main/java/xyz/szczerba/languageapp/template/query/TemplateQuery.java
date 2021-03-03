package xyz.szczerba.languageapp.template.query;

import lombok.Getter;
import lombok.Setter;
import xyz.szczerba.languageapp.utils.Audit;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "templates")
public class TemplateQuery extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
