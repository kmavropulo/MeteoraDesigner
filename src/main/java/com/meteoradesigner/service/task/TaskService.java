package com.meteoradesigner.service.task;

import com.meteoradesigner.dto.TaskDto;
import com.meteoradesigner.model.PointedCompletionState;
import com.meteoradesigner.model.Task;
import com.meteoradesigner.service.GenericService;

import java.util.Set;

//TODO doc
public interface TaskService extends GenericService<Task, TaskDto, Integer> {
    public Set<TaskDto> getExternalTasks(Integer idToGet);

    public Set<TaskDto> getInternalTasks(Integer idToGet);

    //TODO add to dto??!!
    //TODO
    public Set<PointedCompletionState> getPointedCompletionStates(Integer idToGet);

    public Set<TaskDto> getTasksBlockedByTheTask(Integer idToGet);

    public Set<TaskDto> getTasksBlockingTheTask(Integer idToGet);

    public Set<TaskDto> getTasksWithRelativesUnlockedByTheTask(Integer idToGet);

    public Set<TaskDto> getTasksUnlockingTheTaskRelatives(Integer idToGet);
}
