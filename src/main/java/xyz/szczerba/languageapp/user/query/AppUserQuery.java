package xyz.szczerba.languageapp.user.query;

import lombok.Getter;
import lombok.Setter;
import xyz.szczerba.languageapp.utils.Audit;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class AppUserQuery extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
