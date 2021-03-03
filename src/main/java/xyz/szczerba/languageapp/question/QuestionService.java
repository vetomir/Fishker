package xyz.szczerba.languageapp.question;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
class QuestionService {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final QuestionRepository repository;

    QuestionService(final QuestionRepository repository) {
        this.repository = repository;
    }

    List<Question> getAll(){
        return repository.findAll();
    }

    public List<Question> getAll(int page, Sort.Direction sort, String sortBy, int size) {
        return repository.findAll(
                PageRequest.of(
                        page,
                        (size != 0) ? size : DEFAULT_PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        ).getContent();
    }

    Question getSingle(long tagId){
        Optional<Question> entity = repository.findById(tagId);
        if(entity.isEmpty()){
            throw new IllegalArgumentException("Question with id:" + tagId + " is not exists");
        }
        return entity.get();
    }

    Question save(@Valid Question source){
        return repository.save(source);
    }

    Question update(@Valid Question source){
        Question entity = getSingle(source.getId());
        entity.update(source.getQuestion(), source.getAnswer());
        return repository.save(entity);
    }

    Question upgradeLevel(@Valid Question source){
        Question entity = getSingle(source.getId());
        entity.upgradeLevel();
        return repository.save(entity);
    }

    void delete(long id){
        Question entity = getSingle(id);
        repository.delete(entity);
    }
}
