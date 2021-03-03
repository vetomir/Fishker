package xyz.szczerba.languageapp.template;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xyz.szczerba.languageapp.topic.TopicService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
class TemplateService {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final TemplateRepository repository;
    private final TopicService topicService;

    TemplateService(final TemplateRepository repository, final TopicService topicService) {
        this.repository = repository;
        this.topicService = topicService;
    }

    List<Template> getAll(){
        return repository.findAll();
    }

    public List<Template> getAll(int page, Sort.Direction sort, String sortBy, int size) {

        return repository.findAll(
                PageRequest.of(
                        page,
                        (size != 0) ? size : DEFAULT_PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        ).getContent();
    }

    Template getSingle(long tagId){
        Optional<Template> template = repository.findById(tagId);
        if(template.isEmpty()){
            throw new IllegalArgumentException("Template with id:" + tagId + " is not exists");
        }
        return template.get();
    }

    Template save(@Valid Template source, long topicId){
/*        source.setTopic(
                topicService.getSingle(topicId)
        );*/


        return repository.save(source);
    }

    Template update(@Valid Template source){
        Template entity = getSingle(source.getId());
        entity.update(source.getName(),source.getAnswer(),source.getType());
        return repository.save(entity);
    }

    void delete(long id){
        Template entity = getSingle(id);
        repository.delete(entity);
    }

}

