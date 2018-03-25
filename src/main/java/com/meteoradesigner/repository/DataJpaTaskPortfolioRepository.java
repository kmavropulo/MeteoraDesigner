package com.meteoradesigner.repository;

import com.meteoradesigner.model.TaskPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO documentation
//TODO custom methods
public interface DataJpaTaskPortfolioRepository extends JpaRepository<TaskPortfolio,Integer> {
}
