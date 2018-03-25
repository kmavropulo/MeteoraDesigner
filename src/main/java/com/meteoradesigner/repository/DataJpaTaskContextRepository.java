package com.meteoradesigner.repository;

import com.meteoradesigner.model.TaskContext;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface @code{DataJpaTaskContextRepository} declares the data jpa @code{TaskContext}
 * repository.
 */
//TODO custom methods
public interface DataJpaTaskContextRepository extends JpaRepository<TaskContext, Integer> {

    //TODO Query and check good practice how to name/add methods not existing by default
    // TaskContext findTaskContextsByTasks(TaskPortfolio taskPortfolio);
}