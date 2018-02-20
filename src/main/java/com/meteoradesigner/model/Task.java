package com.meteoradesigner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;


/**
 * Class implements task entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends AbstractBaseEntity {

    @NotNull
    private User user;

    @Size(max = 6400000)
    private String description;

    @NotNull
    private LocalDateTime plannedStartTaskTimestamp;

    @NotNull
    private LocalDateTime plannedStopTaskTimestamp;

    @NotNull
    private LocalDateTime actualStartTaskTimestamp;

    @NotNull
    private LocalDateTime actualStopTaskTimestamp;

    private TaskPortfolio portfolio;

    private Set<Task> externalTasks;

    private Set<Task> internalTasks;

    private Set<Task> tasksBlockedByTheTask;

    private Set<Task> tasksBlockingTheTask;

    private Set<TaskContext> contexts;

    private TaskMetric metrics;

    @NotNull
    private SelfCompletionState selfCompletionState;

    private Set<PointedCompletionState> pointedCompletionStates;

    @NotNull
    private InternalExecutionState internalExecutionState;

    /**
     * The deep copying constructor.
     */
    public Task(Task taskToCopy) {
        Task taskCopy = SerializationUtils.clone(taskToCopy);
        taskCopy.setId(0);
        new Task(taskCopy.getId(), taskCopy.getUser(), taskCopy.getDescription(), taskCopy
                .getPlannedStartTaskTimestamp(), taskCopy.getPlannedStopTaskTimestamp(), taskCopy
                .getActualStartTaskTimestamp(), taskCopy.getActualStopTaskTimestamp(), taskCopy
                .getPortfolio(), taskCopy.getExternalTasks(), taskCopy.getInternalTasks(),
                taskCopy.getTasksBlockedByTheTask(), taskCopy.getTasksBlockedByTheTask(),
                taskCopy.getContexts(), taskCopy.getMetrics(), taskCopy.getSelfCompletionState(),
                taskCopy.getPointedCompletionStates(), taskCopy.getInternalExecutionState());
    }


    /**
     * The all-args constructor.
     */
    public Task(Integer id, @NotNull User user, @NotBlank @Size(min = 1, max = 6400000) String
            description, @NotNull LocalDateTime plannedStartTaskTimestamp, @NotNull LocalDateTime
                        plannedStopTaskTimestamp, @NotNull LocalDateTime
                        actualStartTaskTimestamp, @NotNull LocalDateTime actualStopTaskTimestamp,
                TaskPortfolio portfolio, Set<Task> externalTasks, Set<Task> internalTasks,
                Set<Task> tasksBlockedByTheTask, Set<Task> tasksBlockingTheTask, Set<TaskContext>
                        contexts, TaskMetric metrics, @NotNull SelfCompletionState
                        selfCompletionState, Set<PointedCompletionState> pointedCompletionStates,
                @NotNull InternalExecutionState internalExecutionState) {
        super(id);
        this.user = user;
        this.description = description;
        this.plannedStartTaskTimestamp = plannedStartTaskTimestamp;
        this.plannedStopTaskTimestamp = plannedStopTaskTimestamp;
        this.actualStartTaskTimestamp = actualStartTaskTimestamp;
        this.actualStopTaskTimestamp = actualStopTaskTimestamp;
        this.portfolio = portfolio;
        this.externalTasks = externalTasks;
        this.internalTasks = internalTasks;
        this.tasksBlockedByTheTask = tasksBlockedByTheTask;
        this.tasksBlockingTheTask = tasksBlockingTheTask;
        this.contexts = contexts;
        this.metrics = metrics;
        this.selfCompletionState = selfCompletionState;
        this.pointedCompletionStates = pointedCompletionStates;
        this.internalExecutionState = internalExecutionState;
    }

    @Override
    public String toString() {
        return "Task{" +
                "user=" + user +
                ", description='" + description + '\'' +
                ", plannedStartTaskTimestamp=" + plannedStartTaskTimestamp +
                ", plannedStopTaskTimestamp=" + plannedStopTaskTimestamp +
                ", actualStartTaskTimestamp=" + actualStartTaskTimestamp +
                ", actualStopTaskTimestamp=" + actualStopTaskTimestamp +
                ", portfolio=" + portfolio +
                ", externalTasks=" + externalTasks +
                ", internalTasks=" + internalTasks +
                ", tasksBlockedByTheTask=" + tasksBlockedByTheTask +
                ", tasksBlockingTheTask=" + tasksBlockingTheTask +
                ", contexts=" + contexts +
                ", metrics=" + metrics +
                ", selfCompletionState=" + selfCompletionState +
                ", pointedCompletionStates=" + pointedCompletionStates +
                ", internalExecutionState=" + internalExecutionState +
                '}';
    }
}
