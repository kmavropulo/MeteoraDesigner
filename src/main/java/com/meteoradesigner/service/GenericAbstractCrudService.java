package com.meteoradesigner.service;

import com.meteoradesigner.HasId;
import com.meteoradesigner.repository.GenericAbstractCrudRepository;
import com.meteoradesigner.util.validator.ServiceValidatorUtil;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

//TODO doc
@Transactional(readOnly = true)
public abstract class GenericAbstractCrudService<E extends HasId, ID extends Serializable> implements
        GenericService<E, ID> {

    protected abstract GenericAbstractCrudRepository<E, ID> getRepository();

    @Transactional
    @Override
    public E create(E toCreate) {
        //TODO implement Mapper
        //TODO validations 1,2
        return ServiceValidatorUtil.validateNotFoundWithId(getRepository().save(toCreate), toCreate);
    }

    @Override
    public E find(ID toGet) {
        //TODO implement Mapper
        //TODO validations 1,2
        return ServiceValidatorUtil.validateNotFoundWithId(getRepository().findById(toGet).orElse(null), toGet);
    }

    @Override
    public List<E> findAll() {
        //TODO implement Mapper
        //TODO validations 1,2
        return getRepository().findAll();
    }

    @Transactional
    @Override
    public void update(E toUpdate) {
        //TODO implement Mapper
        //TODO validations 1,2
        ServiceValidatorUtil.validateNotFoundWithId(getRepository().save(toUpdate), toUpdate);
    }

    @Transactional
    @Override
    public void delete(ID toDelete) {
        //TODO implement Mapper
        //TODO validations 1,2
        getRepository().deleteById(toDelete);
        ServiceValidatorUtil.validateNotFoundWithId(getRepository().findById(
                toDelete).orElse(null) == null, toDelete);
    }
}