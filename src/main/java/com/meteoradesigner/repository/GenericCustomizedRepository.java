package com.meteoradesigner.repository;

import java.io.Serializable;

//TODO doc
public interface GenericCustomizedRepository<E, ID extends Serializable> {

    <S extends E> S save(S entity);

    E findOne(ID id);

    int delete(int toDelete);
}
