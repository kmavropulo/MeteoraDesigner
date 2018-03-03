package com.meteoradesigner.repository.datajpauserrepository;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.model.User;
import com.meteoradesigner.repository.DataJpaUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static data.DataJpaUserRepositoryTestData.USER_REPOSITORY_FIND_ALL_COMMON_TEST_DATA;
import static java.lang.System.lineSeparator;
import static org.junit.Assert.*;

/**
 * This class @code{DataJpaUserRepositoryCommonTest} runs common tests for
 * @code{DataJpaUserRepository} common methods, that don't included in other test classes.
 */
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class DataJpaUserRepositoryCommonTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            DataJpaUserRepositoryCommonTest.class);

    @Autowired
    DataJpaUserRepository dataJpaUserRepository;

    /**
     * Tests @code{findAll} method that finds all users persisted in repository.
     */
    @Test
    public void findAll() {
        List<User> actual = dataJpaUserRepository.findAll();
        LOGGER.info(String.format("Actual=%s", actual), actual);
        assertEquals(
                String.format("FindAll test failed:" + lineSeparator() + " expected=%s" +
                                lineSeparator() + " actual= %s",
                        USER_REPOSITORY_FIND_ALL_COMMON_TEST_DATA, actual),
                USER_REPOSITORY_FIND_ALL_COMMON_TEST_DATA,
                actual);
    }
}