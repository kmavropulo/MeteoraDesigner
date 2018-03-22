package com.meteoradesigner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

//TODO fix all the documentation, by using this class, -es and dots.

/**
 * Class implements task entity.
 */
@Entity
@Table(name = "tasks", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id",
        "display_name"}, name = "user_id_task_display_name")})
@NoArgsConstructor
@Getter
@Setter
public class Task extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    //TODO debug
    @Column(name = "description", insertable = false, updatable = false)
    @Size(max = 6400000)
    private String description;

    @Column(name = "planned_start_task_timestamp", nullable = false)
    @NotNull
    private LocalDateTime plannedStartTaskTimestamp;

    @Column(name = "planned_stop_task_timestamp", nullable = false)
    @NotNull
    private LocalDateTime plannedStopTaskTimestamp;

    @Column(name = "actual_start_task_timestamp")
    private LocalDateTime actualStartTaskTimestamp = LocalDateTime.MAX;

    @Column(name = "actual_stop_task_timestamp")
    private LocalDateTime actualStopTaskTimestamp = LocalDateTime.MAX;

    @ManyToOne(fetch = FetchType.EAGER)
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
    @AttributeOverrides(value = {
            @AttributeOverride(name = "description", column = @Column(name =
                    "task_metric_description")),
            @AttributeOverride(name = "importance", column = @Column(name =
                    "task_metric_importance")),
            @AttributeOverride(name = "urgency", column = @Column(name = "task_metric_urgency")),
            @AttributeOverride(name = "stability", column = @Column(name =
                    "task_metric_stability")),})
    private TaskMetric metrics;

    //TODO table
    @Enumerated(EnumType.STRING)
    @Column(name = "internal_execution_state")
    @NotBlank
    private InternalExecutionState internalExecutionState;

    //TODO table
    @Enumerated(EnumType.STRING)
    @Column(name = "self_completion_state")
    @NotBlank
    private SelfCompletionState selfCompletionState;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "tasks_pointed_completion_states",
            joinColumns = @JoinColumn(
                    name = "task_id"))
    @Column(name = "pointed_completion_state")
    //TODO lazy?
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<PointedCompletionState> pointedCompletionStates;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "internalTasks")
    private Set<Task> externalTasks;

    //TODO check save and delete
    //TODO table with columns?!
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "tasksBlockingTheTask")
    private Set<Task> tasksBlockedByTheTask;

    //TODO check save and delete
    //TODO table with columns?!
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tasks_blocked_by_the_task_tasks_blocking_the_task",
            joinColumns = {@JoinColumn(
                    name = "task_blocked_by_the_task_id",
                    referencedColumnName = "id",
                    nullable = false)},
            inverseJoinColumns = {@JoinColumn(
                    name = "task_blocking_the_task_id",
                    referencedColumnName = "id",
                    nullable = false)})
    private Set<Task> tasksBlockingTheTask;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "tasksUnlockingTheTaskRelatives")
    private Set<Task> tasksWithRelativesUnlockedByTheTask;

    //TODO check save and delete
    //TODO table with columns?!
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name =
            "tasks_with_relatives_unlocked_by_the_task_tasks_unlocking_the_task_relatives",
            joinColumns = {@JoinColumn(
                    name = "task_with_relatives_unlocked_by_the_task_id",
                    referencedColumnName = "id",
                    nullable = false)},
            inverseJoinColumns = {@JoinColumn(
                    name = "task_unlocking_the_task_relatives_id",
                    referencedColumnName = "id",
                    nullable = false)})
    private Set<Task> tasksUnlockingTheTaskRelatives;

    /**
     * The deep copying constructor.
     */
    //TODO fix
    public Task(Task taskToCopy) {
        Task taskCopy = SerializationUtils.clone(taskToCopy);
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
                "user=" + user.getDisplayName() +
                ", description='" + description + '\'' +
                ", plannedStartTaskTimestamp=" + plannedStartTaskTimestamp +
                ", plannedStopTaskTimestamp=" + plannedStopTaskTimestamp +
                ", actualStartTaskTimestamp=" + actualStartTaskTimestamp +
                ", actualStopTaskTimestamp=" + actualStopTaskTimestamp +
                ", portfolio=" + portfolio.getDisplayName() +
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
