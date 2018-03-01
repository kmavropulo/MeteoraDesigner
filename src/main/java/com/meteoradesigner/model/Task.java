package com.meteoradesigner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

/**
 * Class implements task entity.
 */
@Entity
@Table(name = "tasks", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id",
        "display_name"}, name = "user_id_task_display_name")})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task extends AbstractNamedEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    //TODO debug
    @Column(name = "description",insertable=false, updatable=false)
    @Size(max = 6400000)
    private String description;

    @Column(name = "planned_start_task_time_stamp", nullable = false)
    @NotNull
    private LocalDateTime plannedStartTaskTimestamp;

    @Column(name = "planned_stop_task_time_stamp", nullable = false)
    @NotNull
    private LocalDateTime plannedStopTaskTimestamp;

    @Column(name = "actual_start_task_time_stamp")
    private LocalDateTime actualStartTaskTimestamp;

    @Column(name = "actual_stop_task_time_stamp")
    private LocalDateTime actualStopTaskTimestamp;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private TaskPortfolio portfolio;

    //TODO table with columns
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "tasks_contexts",
            joinColumns = {@JoinColumn(name = "task_id",
                    referencedColumnName = "id",
                    nullable = false),
            },
            inverseJoinColumns = {@JoinColumn(name = "context_id",
                    referencedColumnName = "id",
                    nullable = false)})
    private Set<TaskContext> contexts;

    @Embedded
    private TaskMetric metrics;

    @Enumerated(EnumType.STRING)
    @NotNull
    private InternalExecutionState internalExecutionState;

    @Enumerated(EnumType.STRING)
    @NotNull
    private SelfCompletionState selfCompletionState;

    //TODO table with columns
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "tasks_pointed_completion_states",
            joinColumns = @JoinColumn(
                    name = "task_id"))
    @Column(name = "pointed_completion_state")
    //TODO lazy?
    @ElementCollection(fetch = FetchType.EAGER)
    @NotEmpty
    private Set<PointedCompletionState> pointedCompletionStates;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "internalTasks")
    private Set<Task> externalTasks;

    //TODO check save and delete
    //TODO table with columns?!
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "external_tasks_internal_tasks",
            joinColumns = {@JoinColumn(
                    name = "external_task_id",
                    referencedColumnName = "id",
                    nullable = false)},
            inverseJoinColumns = {@JoinColumn(
                    name = "internal_task_id",
                    referencedColumnName = "id",
                    nullable = false)})
    private Set<Task> internalTasks;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tasksBlockingTheTask")
    private Set<Task> tasksBlockedByTheTask;

    //TODO check save and delete
    //TODO table with columns?!
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tasks_blocked_by_the_task_tasks_blocking_the_task",
            joinColumns = {@JoinColumn(
                    name = "tasks_blocked_by_the_task",
                    referencedColumnName = "id",
                    nullable = false)},
            inverseJoinColumns = {@JoinColumn(
                    name = "tasks_blocking_the_task",
                    referencedColumnName = "id",
                    nullable = false)})
    private Set<Task> tasksBlockingTheTask;

    /**
     * The deep copying constructor.
     */
    public Task(Task taskToCopy) {
        Task taskCopy = SerializationUtils.clone(taskToCopy);
        taskCopy.setId(0);
        new Task(taskCopy.getId(), taskCopy.getDisplayName(), taskCopy.getUser(), taskCopy
                .getDescription(), taskCopy.getPlannedStartTaskTimestamp(), taskCopy
                .getPlannedStopTaskTimestamp(), taskCopy.getActualStartTaskTimestamp(), taskCopy
                .getActualStopTaskTimestamp(), taskCopy.getPortfolio(), taskCopy.getExternalTasks
                (), taskCopy.getInternalTasks(), taskCopy.getTasksBlockedByTheTask(), taskCopy
                .getTasksBlockedByTheTask(), taskCopy.getContexts(), taskCopy.getMetrics(),
                taskCopy.getSelfCompletionState(), taskCopy.getPointedCompletionStates(),
                taskCopy.getInternalExecutionState());
    }

    /**
     * The minimum parameters initializing constructor.
     */
    public Task(String displayNameToSet, @NotNull User user, @NotBlank @Size(min = 1,
            max = 6400000) String description, @NotNull LocalDateTime plannedStartTaskTimestamp,
                @NotNull LocalDateTime plannedStopTaskTimestamp, @NotNull SelfCompletionState
                        selfCompletionState, @NotNull InternalExecutionState
                        internalExecutionState) {
        this(0, displayNameToSet, user, description, plannedStartTaskTimestamp,
                plannedStopTaskTimestamp, null, null,
                null, Collections.emptySet(), Collections.emptySet(), Collections.emptySet(),
                Collections.emptySet(), Collections.emptySet(), new TaskMetric(),
                selfCompletionState, Collections.emptySet(),
                internalExecutionState);
    }

    /**
     * The all-args constructor.
     */
    public Task(Integer id, String displayNameToSet, @NotNull User user, @NotBlank @Size(min = 1,
            max = 6400000) String description, @NotNull LocalDateTime plannedStartTaskTimestamp,
                @NotNull LocalDateTime plannedStopTaskTimestamp, LocalDateTime
                        actualStartTaskTimestamp, LocalDateTime actualStopTaskTimestamp,
                TaskPortfolio portfolio, Set<Task> externalTasks, Set<Task> internalTasks,
                Set<Task> tasksBlockedByTheTask, Set<Task> tasksBlockingTheTask, Set<TaskContext>
                        contexts, TaskMetric metrics, @NotNull SelfCompletionState
                        selfCompletionState, Set<PointedCompletionState> pointedCompletionStates,
                @NotNull InternalExecutionState internalExecutionState) {
        super(id, displayNameToSet);
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
