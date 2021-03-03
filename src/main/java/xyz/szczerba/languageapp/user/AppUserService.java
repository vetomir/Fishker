package xyz.szczerba.languageapp.user;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
class AppUserService {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private final AppUserRepository repository;

    AppUserService(final AppUserRepository repository) {
        this.repository = repository;
    }

    List<AppUser> getAll(){
        return repository.findAll();
    }

    public List<AppUser> getAll(int page, Sort.Direction sort, String sortBy, int size) {
        return repository.findAll(
                PageRequest.of(
                        page,
                        (size != 0) ? size : DEFAULT_PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        ).getContent();
    }

    AppUser getSingle(long tagId){
        Optional<AppUser> entity = repository.findById(tagId);
        if(entity.isEmpty()){
            throw new IllegalArgumentException("User with id:" + tagId + " is not exists");
        }
        return entity.get();
    }

    AppUser save(@Valid AppUser source){
        return repository.save(source);
    }

    AppUser update(@Valid AppUser source){
        AppUser entity = getSingle(source.getId());
        entity.updateProfile(entity.getNickname(),entity.getUsername(),entity.getPhotoUrl());
        return repository.save(entity);
    }
    AppUser updatePassword(long id, String password, String passwordRepeat){
        AppUser entity = getSingle(id);
        entity.updatePassword(password,passwordRepeat);
        return repository.save(entity);
    }

    void delete(long id){
        AppUser entity = getSingle(id);
        repository.delete(entity);
    }
}
