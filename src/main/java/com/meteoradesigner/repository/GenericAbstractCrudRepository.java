package com.meteoradesigner.repository;

import java.io.Serializable;
import java.util.List;

//TODO doc
public interface GenericAbstractCrudRepository<E, ID extends Serializable> {

    public <S extends E> S save(S entity);

    public E findOne(ID id);

    public List<E> findAll();

    ID deleteById(ID id);
}