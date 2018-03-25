package com.meteoradesigner.repository;

import com.meteoradesigner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO documentation
//TODO custom methods
public interface DataJpaTaskRepository extends JpaRepository<Task, Integer> {
}
