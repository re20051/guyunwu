package com.example.guyunwu.service.base;

import com.example.guyunwu.exception.ResourceNotFoundException;
import com.example.guyunwu.repository.BaseRepository;
import com.example.guyunwu.service.CrudService;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<ENTITY, ID> implements CrudService<ENTITY, ID> {

    private final String entityName;

    private final BaseRepository<ENTITY, ID> repository;

    protected final Class<ENTITY> entityClass;

    @SuppressWarnings("unchecked")
    protected AbstractCrudService(BaseRepository<ENTITY, ID> repository) {
        this.repository = repository;

        this.entityClass = (Class<ENTITY>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityName = entityClass.getSimpleName();
    }

    @Override
    public ENTITY create(ENTITY entity) {
        Assert.notNull(entity, entityName + " must not be null");
        return repository.save(entity);
    }

    @Override
    public Collection<ENTITY> create(Collection<ENTITY> entities) {
        Assert.notNull(entities, entityName + " must not be null");
        return repository.saveAll(entities);
    }

    @Override
    public List<ENTITY> listAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ENTITY> getById(ID id) {
        Assert.notNull(id, "Id must not be null");
        return repository.findById(id);
    }

    @Override
    public ENTITY getNotNullById(ID id) {
        Assert.notNull(id, "Id must not be null");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName + "(id: " + id + ") could not be found"));
    }

    @Override
    public ENTITY update(ENTITY entity) {
        Assert.notNull(entity, entityName + " must not be null");
        return repository.save(entity);
    }

    @Override
    public Collection<ENTITY> update(Collection<ENTITY> entities) {
        Assert.notNull(entities, entityName + " must not be null");
        return repository.saveAll(entities);
    }

    @Override
    public ENTITY deleteById(ID id) {
        Assert.notNull(id, "Id must not be null");
        ENTITY entity = getNotNullById(id);
        repository.delete(entity);
        return entity;
    }

    @Override
    public long count() {
        return repository.count();
    }
}
