package com.meteoradesigner.service.portfolio;

import com.meteoradesigner.model.TaskPortfolio;
import com.meteoradesigner.repository.DataJpaTaskPortfolioRepository;
import com.meteoradesigner.repository.GenericAbstractCrudRepository;
import com.meteoradesigner.service.GenericAbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO doc
@Service
@Transactional(readOnly = true)
public class TaskPortfolioImp extends GenericAbstractCrudService<TaskPortfolio, Integer> implements TaskPortfolioService {

    @Autowired
    private DataJpaTaskPortfolioRepository repository;

    @Override
    protected GenericAbstractCrudRepository<TaskPortfolio, Integer> getRepository() {
        return repository;
    }
}
