package com.meteoradesigner.service;

import com.meteoradesigner.HasId;
import com.meteoradesigner.repository.GenericCustomizedRepository;
import com.meteoradesigner.util.ServiceValidatorUtil;

import java.io.Serializable;

//TODO doc
public abstract class GenericAbstractCrudService<E extends HasId, ID extends Serializable> implements
        GenericService<E, ID> {

    protected abstract GenericCustomizedRepository<E, ID> getRepository();

    @Override
    public E create(E toCreate) {
        //TODO implement Mapper
        //TODO validations 1,2
        return ServiceValidatorUtil.validateNotFoundWithId(getRepository().save(toCreate), toCreate);
    }

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

    @Override
    public void delete(int toDelete) {
        //TODO implement Mapper
        //TODO validations 1,2
        ServiceValidatorUtil.validateConditionNotFoundWithId((getRepository().delete(toDelete)) == 0,
                toDelete);
    }
}

