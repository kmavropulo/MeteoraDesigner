package com.meteoradesigner.repository.datajpataskcontextrepository;

import com.meteoradesigner.repository.config.DataJpaRepositoryTestConfiguration;
import com.meteoradesigner.repository.DataJpaTaskContextRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class @code{DataJpaTaskContextRepositoryCustomTest} runs custom tests for
 *
 * @code{DataJpaTaskContextRepository}'s custom methods.
 */
//TODO documentation.
public class DataJpaTaskContextRepositoryCustomTest extends DataJpaRepositoryTestConfiguration{

    @Autowired
    private DataJpaTaskContextRepository dataJpaTaskContextRepository;

    /**
     * Tests @code{findTaskContextsByTasks} method.
     */
    @Test
    public void findTaskContextsByTasks() {
        //TODO saveOneTest find by portfolioId
    }
}