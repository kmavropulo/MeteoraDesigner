package com.meteoradesigner.service.context;

import com.meteoradesigner.model.TaskContext;
import com.meteoradesigner.repository.DataJpaTaskContextRepository;
import com.meteoradesigner.repository.GenericAbstractCrudRepository;
import com.meteoradesigner.service.GenericAbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO doc
@Service
//@Transactional(readOnly = true)
public class TaskContextImp extends GenericAbstractCrudService<TaskContext, Integer> implements TaskContextService {

    @Autowired
    private DataJpaTaskContextRepository repository;

    @Override
    protected GenericAbstractCrudRepository<TaskContext, Integer> getRepository() {
        return repository;
    }
}