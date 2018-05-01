package com.meteoradesigner.service;

import com.meteoradesigner.HasId;

import java.io.Serializable;
import java.util.List;

//TODO doc
public interface GenericService<E extends HasId, ID extends Serializable> {
    E create(E toCreate);

    void update(E toUpdate);

    E find(ID toget);

    void delete(ID toDelete);

    List<E> findAll();
}
