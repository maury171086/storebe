package com.online.store.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.online.store.dto.BaseDto;

public interface GenericService<T extends BaseDto> {

    /**
     * find all entities
     *
     * @param pageable
     * @return list of entities
     */
    Page<T> findAll(Pageable pageable);

    /**
     * create entity
     *
     * @param entity the model
     * @return a entity
     */
    T createEntity(T entity);

    /**
     * update the entity
     *
     * @param entity the model to update
     * @return the model updated
     */
    T updateEntity(T entity);

    /**
     * delete the entity
     *
     * @param id the id of entity model
     * @return the model was deleted
     */
    T deleteEntity(Long id);
}
