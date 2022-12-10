package com.example.guyunwu.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CrudService<ENTITY, ID> {

    ENTITY create(ENTITY entity);

    Collection<ENTITY> create(Collection<ENTITY> entities);

    List<ENTITY> listAll();

    Optional<ENTITY> getById(ID id);

    ENTITY getNotNullById(ID id);

    ENTITY update(ENTITY entity);

    Collection<ENTITY> update(Collection<ENTITY> entities);

    ENTITY deleteById(ID id);

    long deleteByIdIn(Collection<ID> ids);

    long count();
}
