package com.meteoradesigner.service.task;

import com.meteoradesigner.model.Task;
import com.meteoradesigner.repository.DataJpaTaskRepository;
import com.meteoradesigner.repository.GenericAbstractCrudRepository;
import com.meteoradesigner.service.GenericAbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO doc
@Service
//@Transactional(readOnly = true)
public class TaskServiceImp extends GenericAbstractCrudService<Task, Integer> implements TaskService {

    @Autowired
    private DataJpaTaskRepository repository;

    @Override
    protected GenericAbstractCrudRepository<Task, Integer> getRepository() {
        return repository;
    }
}
