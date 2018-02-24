package com.meteoradesigner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class implements the task portfolio entity.
 */
@Entity
@Table(name = "portfolios", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id",
        "display_name"}, name = "portfolio_unique_user_id_display_name")})
@NoArgsConstructor
@Getter
@Setter
public class TaskPortfolio extends AbstractNamedEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "description")
    @Size(max = 6400)
    private String description;

    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER)
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
