package com.meteoradesigner.service.portfolio;

import com.meteoradesigner.dto.TaskPortfolioDto;
import com.meteoradesigner.model.TaskPortfolio;
import com.meteoradesigner.repository.DataJpaTaskPortfolioRepository;
import com.meteoradesigner.repository.GenericAbstractCrudRepository;
import com.meteoradesigner.service.GenericAbstractCrudService;
import com.meteoradesigner.util.mapper.EntityDtoMapper;
import com.meteoradesigner.util.mapper.TaskPortfolioEntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This @code{TaskPortfolioServiceImp} class implements @code{TaskPortfolioService} interface.
 */
@Service
//@Transactional(readOnly = true)
public class TaskPortfolioServiceImp extends GenericAbstractCrudService<TaskPortfolio,
        TaskPortfolioDto, Integer> implements TaskPortfolioService {

    @Autowired
    private DataJpaTaskPortfolioRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    protected GenericAbstractCrudRepository<TaskPortfolio, Integer> getRepository() {
        return repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EntityDtoMapper<TaskPortfolio, TaskPortfolioDto> getEntityDtoMapper() {
        return new TaskPortfolioEntityDtoMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<? extends TaskPortfolioDto> getDtoClass() {
        return TaskPortfolioDto.class;
    }
}
