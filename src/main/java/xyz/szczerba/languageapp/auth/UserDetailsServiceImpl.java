package xyz.szczerba.languageapp.auth;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import xyz.szczerba.languageapp.user.AppUserRepository;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository repository;

    public UserDetailsServiceImpl(final AppUserRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(final String s) {
        UserDetails user = repository.findAllByUsername(s);
        if (user == null){
            throw new IllegalAccessException("User with this email address is not present");
        }
        return user;

    }
}
