package com.meteoradesigner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * TaskMetric - not the entity, default parameters set for a task.
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskMetric implements Serializable {

    @NotNull
    @Size(min = 1, max = 6400)
    private String description;

    private Long importance;
    private Long urgency;
    private Long stability;

    public TaskMetric(TaskMetric taskMetricToCopy) {
        this(taskMetricToCopy.getDescription(), taskMetricToCopy.getImportance(),
                taskMetricToCopy.getUrgency(), taskMetricToCopy.getStability());
    }

    public TaskMetric(@NotNull @Size(min = 1, max = 6400) String description, Long importance,
                      Long urgency, Long stability) {
        this.description = description;
        this.importance = importance;
        this.urgency = urgency;
        this.stability = stability;
    }

    @Override
    public String toString() {
        return "TaskMetric{" +
                "description='" + description + '\'' +
                ", importance=" + importance +
                ", urgency=" + urgency +
                ", stability=" + stability +
                '}';
    }
}
