package com.meteoradesigner.repository;

import com.meteoradesigner.model.TaskPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

//TODO documentation
//TODO custom methods
@Transactional
public interface DataJpaTaskPortfolioRepository extends JpaRepository<TaskPortfolio, Integer>,
        GenericAbstractCrudRepository<TaskPortfolio, Integer> {
}
