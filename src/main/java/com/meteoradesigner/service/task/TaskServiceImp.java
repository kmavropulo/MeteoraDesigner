package com.meteoradesigner.service.task;

import com.meteoradesigner.dto.TaskDto;
import com.meteoradesigner.model.Task;
import com.meteoradesigner.repository.DataJpaTaskRepository;
import com.meteoradesigner.repository.GenericAbstractCrudRepository;
import com.meteoradesigner.service.GenericAbstractCrudService;
import com.meteoradesigner.util.mapper.EntityDtoMapper;
import com.meteoradesigner.util.mapper.TaskEntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This @code{TaskServiceImp} class implements @code{TaskService} interface.
 */
@Service
//@Transactional(readOnly = true)
public class TaskServiceImp extends GenericAbstractCrudService<Task, TaskDto, Integer> implements
                                                                                       TaskService {

    @Autowired
    private DataJpaTaskRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    protected GenericAbstractCrudRepository<Task, Integer> getRepository() {
        return repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EntityDtoMapper<Task, TaskDto> getEntityDtoMapper() {
        return new TaskEntityDtoMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends TaskDto> getDtoClass() {
        return TaskDto.class;
    }
}
