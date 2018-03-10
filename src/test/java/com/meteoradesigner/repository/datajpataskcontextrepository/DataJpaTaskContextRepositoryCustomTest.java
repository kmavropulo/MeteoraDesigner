package com.meteoradesigner.repository.datajpataskcontextrepository;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.repository.DataJpaTaskContextRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * This class @code{DataJpaTaskContextRepositoryCustomTest} runs custom tests for
 *
 * @code{DataJpaTaskContextRepository}'s custom methods.
 */
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class DataJpaTaskContextRepositoryCustomTest {

    @Autowired
    private DataJpaTaskContextRepository dataJpaTaskContextRepository;

    /**
     * Tests @code{findTaskContextsByTasks} method.
     */
    @Test
    public void findTaskContextsByTasks() {
        //TODO test find by portfolioId
    }
}