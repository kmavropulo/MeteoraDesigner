package com.meteoradesigner.service.task;

import com.meteoradesigner.dto.TaskDto;
import com.meteoradesigner.model.PointedCompletionState;
import com.meteoradesigner.model.Task;
import com.meteoradesigner.repository.DataJpaTaskRepository;
import com.meteoradesigner.repository.GenericAbstractCrudRepository;
import com.meteoradesigner.service.GenericAbstractCrudService;
import com.meteoradesigner.util.crud.DeleteSupport;
import com.meteoradesigner.util.mapper.EntityDtoMapper;
import com.meteoradesigner.util.mapper.TaskEntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This @code{TaskServiceImp} class implements @code{TaskService} interface.
 */
@Service
//@Transactional(readOnly = true)
public class TaskServiceImp extends GenericAbstractCrudService<Task, TaskDto, Integer> implements
                                                                                       TaskService {

    @Autowired
    private DataJpaTaskRepository repository;

    @Autowired
    @Qualifier(value = "taskDeleteSupport")
    private DeleteSupport<Integer> deleteSupport;

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

    /**
     * {@inheritDoc}
     */
    @Override
    protected Consumer<Integer> getDeleteSupport() {
        return deleteSupport.getDeleteSupportConsumer();
    }

    public Set<TaskDto> getTaskDtoSet(Integer idToGet,
                                      Function<Task, Set<Task>> function) {
        Set<Task> externalTasks = function.apply(getEntity(idToGet));

        return mapEntitiesToDtoSet(externalTasks);
    }

    private Set<TaskDto> mapEntitiesToDtoSet(Set<Task> externalTasks) {
        return externalTasks.stream()
                            .map(task -> getEntityDtoMapper().entityToDto(task))
                            .collect(Collectors.toSet());
    }

    @Override
    public Set<TaskDto> getExternalTasks(Integer idToGet) {
        return getTaskDtoSet(idToGet, id -> getEntity(idToGet).getExternalTasks());
    }

    @Override
    public Set<TaskDto> getInternalTasks(Integer idToGet) {
        return getTaskDtoSet(idToGet, id -> getEntity(idToGet).getInternalTasks());
    }

    @Override
    public Set<PointedCompletionState> getPointedCompletionStates(Integer idToGet) {
        return getEntity(idToGet).getPointedCompletionStates();
    }

    @Override
    public Set<TaskDto> getTasksBlockedByTheTask(Integer idToGet) {
        return getTaskDtoSet(idToGet, id -> getEntity(idToGet).getTasksBlockedByTheTask());
    }

    @Override
    public Set<TaskDto> getTasksBlockingTheTask(Integer idToGet) {
        return getTaskDtoSet(idToGet, id -> getEntity(idToGet).getTasksBlockingTheTask());
    }

    @Override
    public Set<TaskDto> getTasksWithRelativesUnlockedByTheTask(Integer idToGet) {
        return getTaskDtoSet(idToGet, id -> getEntity(idToGet).getTasksWithRelativesUnlockedByTheTask());
    }

    @Override
    public Set<TaskDto> getTasksUnlockingTheTaskRelatives(Integer idToGet) {
        return getTaskDtoSet(idToGet, id -> getEntity(idToGet).getTasksUnlockingTheTaskRelatives());
    }
}