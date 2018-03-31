package com.meteoradesigner.repository;

import com.meteoradesigner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

//TODO documentation
//TODO custom methods
@Transactional
public interface DataJpaTaskRepository extends JpaRepository<Task, Integer>,
        GenericAbstractCrudRepository<Task, Integer> {
}
