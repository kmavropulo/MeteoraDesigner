package com.meteoradesigner.repository.datajpauserrepository;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.model.User;
import com.meteoradesigner.repository.DataJpaUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.GenericTestHelper;

import static data.DataJpaUserRepositoryTestData.USER_REPOSITORY_FIND_ALL_COMMON_TEST_DATA;

/**
 * This class @code{DataJpaUserRepositoryCommonTest} runs common tests for
 *
 * @code{DataJpaUserRepository} common methods, that don't included in other test classes.
 */
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class DataJpaUserRepositoryCommonTest {

    @Autowired
    DataJpaUserRepository dataJpaUserRepository;

    /**
     * Tests @code{findAll} method that finds all users persisted in repository.
     */
    @Test
    public void findAll() {
        new GenericTestHelper<User>().findAll(dataJpaUserRepository,
                USER_REPOSITORY_FIND_ALL_COMMON_TEST_DATA);
    }
}