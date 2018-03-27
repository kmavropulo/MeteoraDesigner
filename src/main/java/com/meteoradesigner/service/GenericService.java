package com.meteoradesigner.service;

import com.meteoradesigner.HasId;

import java.io.Serializable;

//TODO doc
public interface GenericService<E extends HasId, ID extends Serializable> {
    E create(E toCreate);

    E update(E toUpdate);

    E get(ID toget);

    void delete(int toDelete);
}
