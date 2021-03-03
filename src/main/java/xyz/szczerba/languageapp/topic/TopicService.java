package xyz.szczerba.languageapp.topic;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public
class TopicService {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final TopicRepository repository;

    TopicService(final TopicRepository repository) {
        this.repository = repository;
    }

    List<Topic> getAll(){
        return repository.findAll();
    }

    public List<Topic> getAll(int page, Sort.Direction sort, String sortBy, int size) {
        return repository.findAll(
                PageRequest.of(
                        page,
                        (size != 0) ? size : DEFAULT_PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        ).getContent();
    }

    public Topic getSingle(long tagId){
        Optional<Topic> entity = repository.findById(tagId);
        if(entity.isEmpty()){
            throw new IllegalArgumentException("Topic with id:" + tagId + " is not exists");
        }
        return entity.get();
    }

    Topic save(@Valid Topic source){
        return repository.save(source);
    }

    Topic update(@Valid Topic source){
        Topic entity = getSingle(source.getId());
        entity.update(source.getTitle());
        return repository.save(entity);
    }

    void delete(long id){
        Topic entity = getSingle(id);
        repository.delete(entity);
    }
}
