package com.meteoradesigner.service.context;

import com.meteoradesigner.model.TaskContext;
import com.meteoradesigner.repository.DataJpaTaskContextRepository;
import com.meteoradesigner.service.GenericAbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO doc
@Service
@Transactional(readOnly = true)
public class TaskContextImp extends GenericAbstractCrudService<TaskContext, Integer> implements TaskContextService {

    @Autowired
    private DataJpaTaskContextRepository repository;

    @Override
    protected JpaRepository<TaskContext, Integer> getRepository() {
        return repository;
    }
}
