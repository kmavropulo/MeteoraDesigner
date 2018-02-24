package com.meteoradesigner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * TaskMetric - not the entity, default parameters set for a task.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskMetric implements Serializable {

    @Size(max = 6400)
    private String description;

    private Long importance;
    private Long urgency;
    private Long stability;

    /**
     * Copy constructor.
     */
    public TaskMetric(TaskMetric taskMetricToCopy) {
        this(taskMetricToCopy.getDescription(), taskMetricToCopy.getImportance(),
                taskMetricToCopy.getUrgency(), taskMetricToCopy.getStability());
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
