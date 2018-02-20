package com.meteoradesigner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class implements the task portfolio entity.
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskPortfolio extends AbstractNamedEntity {

    @NotNull
    private User user;

    @Size(max = 6400)
    private String description;

    private Set<Task> tasks;

    /**
     * Deep copy constructor.
     */
    public TaskPortfolio(TaskPortfolio taskPortfolio) {
        TaskPortfolio taskPortfolioCopy = SerializationUtils.clone(taskPortfolio);
        taskPortfolioCopy.setId(0);
        new TaskPortfolio(taskPortfolioCopy.getId(), taskPortfolioCopy.getDisplayName(),
                taskPortfolioCopy.getUser(), taskPortfolioCopy.getDescription(),
                taskPortfolioCopy.getTasks());
    }

    /**
     * The all-args constructors.
     */
    public TaskPortfolio(Integer idToSet, String nameToSet, @NotNull User user, @NotNull @Size
            (min = 1, max = 6400) String description, Set<Task> tasks) {
        super(idToSet, nameToSet);
        this.user = user;
        this.description = description;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "TaskPortfolio{" +
                "user=" + user +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
