package de.eterium.core.data.service;

import java.util.List;
import java.util.UUID;

public interface DataService<E> {

    E get(UUID id);
    E getOrCreate(UUID id);
    void saveOrUpdate(E entity);
    //do we need this? NOIDONTTHINKSO void update(E entity);
    void delete(E entity);
    void saveAll(List<E> entities);

}