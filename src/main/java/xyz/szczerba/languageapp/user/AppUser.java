package xyz.szczerba.languageapp.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.szczerba.languageapp.question.query.QuestionQuery;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
class AppUser implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nickname;

    @Email
    private String username;
    private String password;

    private String photoUrl;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id",  insertable = false)
    private Set<QuestionQuery> question = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    void updateProfile(String nickname, String username, String photoUrl){
        this.nickname = nickname;
        this.username = username;
        this.photoUrl = photoUrl;
    }
    void updatePassword(String password, String passwordRepeat){
        if(password.equals(passwordRepeat)){
            this.password = password;
        }
        else throw new IllegalArgumentException("Password needs to be the same");
    }
}
