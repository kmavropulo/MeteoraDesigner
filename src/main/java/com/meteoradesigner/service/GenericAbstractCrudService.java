package com.meteoradesigner.service;

import com.meteoradesigner.HasId;
import com.meteoradesigner.util.ServiceValidatorUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

//TODO doc
@Transactional(readOnly = true)
public abstract class GenericAbstractCrudService<E extends HasId, ID extends Serializable> implements
        GenericService<E, ID> {

    protected abstract JpaRepository<E, ID> getRepository();

    @Transactional
    @Override
    public E create(E toCreate) {
        //TODO implement Mapper
        //TODO validations 1,2
        return ServiceValidatorUtil.validateNotFoundWithId(getRepository().save(toCreate), toCreate);
    }

    @Transactional
    @Override
    public E update(E toUpdate) {
        //TODO implement Mapper
        //TODO validations 1,2
        return ServiceValidatorUtil.validateNotFoundWithId(getRepository().save(toUpdate), toUpdate);
    }

    @Override
    public E get(ID toGet) {
        //TODO implement Mapper
        //TODO validations 1,2
        return ServiceValidatorUtil.validateNotFoundWithId(getRepository().findOne(toGet), toGet);
    }

    @Transactional
    @Override
    public boolean delete(ID toDelete) {
        //TODO implement Mapper
        //TODO validations 1,2

        getRepository().delete(toDelete);
        return ServiceValidatorUtil.validateNotFoundWithIdBoolean(getRepository().findOne(toDelete)
                == null, toDelete);
    }

    @Override
    public List<E> findAll() {
        //TODO implement Mapper
        //TODO validations 1,2
        return getRepository().findAll();
    }
}