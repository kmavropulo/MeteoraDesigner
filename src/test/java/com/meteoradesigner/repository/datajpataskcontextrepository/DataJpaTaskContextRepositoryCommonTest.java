package com.meteoradesigner.repository.datajpataskcontextrepository;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.model.TaskContext;
import com.meteoradesigner.repository.DataJpaTaskContextRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.GenericTestHelper;

import static data.DataJpaTaskContextRepositoryTestData
        .TASK_CONTEXT_REPOSITORY_FIND_ALL_COMMON_TEST_DATA;


/**
 * This class @code{DataJpaTaskContextRepositoryCommonTest} runs common tests for
 *
 * @code{DataJpaTaskContextRepository} common methods, that don't included in other test classes.
 */
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class DataJpaTaskContextRepositoryCommonTest {

    @Autowired
    DataJpaTaskContextRepository dataJpaTaskContextRepository;

    /**
     * Tests @code{findAll} method that finds all taskContexts persisted in repository.
     */
    @Test
    public void findAll() {
        new GenericTestHelper<TaskContext>().findAll(dataJpaTaskContextRepository,
                TASK_CONTEXT_REPOSITORY_FIND_ALL_COMMON_TEST_DATA);
    }
}