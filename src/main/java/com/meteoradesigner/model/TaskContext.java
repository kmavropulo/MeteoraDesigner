package com.meteoradesigner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class implements task context entity.
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskContext extends AbstractBaseEntity {

    @NotNull
    private User user;

    @NotNull
    @Size(min = 1, max = 6400)
    private String description;

    private Set<TaskContext> externalContext;
    private Set<TaskContext> internalContext;

    /**
     * The deep copying constructor.
     */
    public TaskContext(TaskContext taskContextToCopy) {
        TaskContext taskContextCopy = SerializationUtils.clone(taskContextToCopy);
        taskContextCopy.setId(0);
        new TaskContext(taskContextCopy.getId(), taskContextCopy.getUser(), taskContextCopy
                .getDescription(), taskContextCopy.getExternalContext(), taskContextCopy
                .getInternalContext());
    }

    /**
     * The all-args constructor.
     */
    public TaskContext(Integer id, @NotNull User user, @NotNull @Size(min = 1, max = 6400) String
            description, Set<TaskContext> externalContext, Set<TaskContext> internalContext) {
        super(id);
        this.user = user;
        this.description = description;
        this.externalContext = externalContext;
        this.internalContext = internalContext;
    }

    @Override
    public String toString() {
        return "TaskContext{" +
                "user=" + user +
                ", description='" + description + '\'' +
                ", externalContext=" + externalContext +
                ", internalContext=" + internalContext +
                '}';
    }
}
