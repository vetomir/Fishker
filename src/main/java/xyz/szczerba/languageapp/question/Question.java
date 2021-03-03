package xyz.szczerba.languageapp.question;

import lombok.Getter;
import lombok.Setter;
import xyz.szczerba.languageapp.template.query.TemplateQuery;
import xyz.szczerba.languageapp.user.query.AppUserQuery;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String question;
    private String answer;

    private Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", updatable = false)
    private AppUserQuery appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", updatable = false)
    private TemplateQuery template;

    void update(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    void upgradeLevel(){
        if(!level.equals(Level.FIFTH)){
            Level[] values = Level.values();
            level = values[level.getVal() + 1];
        }
    }
}
