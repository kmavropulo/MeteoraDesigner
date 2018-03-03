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

import static data.DataJpaUserRepositoryTestData
        .USER_REPOSITORY_DISPLAY_NAME_TO_FIND_BY_DISPLAY_NAME;
import static data.DataJpaUserRepositoryTestData.USER_REPOSITORY_FIND_ALL_COMMON_TEST_DATA;
import static data.DataJpaUserRepositoryTestData.USER_REPOSITORY_MAIL_TO_FIND_BY_MAIL;
import static data.DataJpaUserRepositoryTestData.USER_REPOSITORY_CUSTOM_TEST_DATA;
import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

/**
 * This class @code{DataJpaUserRepositoryCustomTest} runs custom tests for
 * @code{DataJpaUserRepository}'s custom methods.
 */
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class DataJpaUserRepositoryCustomTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataJpaUserRepositoryCustomTest
            .class);

    @Autowired
    private DataJpaUserRepository dataJpaUserRepository;

    /**
     * Tests @code{findByEmail} method.
     */
    @Test
    public void findByEmail() {
        LOGGER.info(String.format("Mail=%s", USER_REPOSITORY_MAIL_TO_FIND_BY_MAIL),
                USER_REPOSITORY_MAIL_TO_FIND_BY_MAIL);
        User actual = dataJpaUserRepository.findByEmail(
                USER_REPOSITORY_MAIL_TO_FIND_BY_MAIL);
        LOGGER.info(String.format("Actual=%s", actual), actual);
        assertEquals(
                String.format("FindByMail test failed:" + lineSeparator() + " expected=%s" +
                                lineSeparator() + " actual= %s",
                        USER_REPOSITORY_FIND_ALL_COMMON_TEST_DATA, actual),
                USER_REPOSITORY_CUSTOM_TEST_DATA,
                actual);
    }

    /**
     * Tests @code{findByDisplayName} method.
     */
    @Test
    public void findByDisplayName() {
        LOGGER.info(String.format("DisplayName=%s",
                USER_REPOSITORY_DISPLAY_NAME_TO_FIND_BY_DISPLAY_NAME),
                USER_REPOSITORY_DISPLAY_NAME_TO_FIND_BY_DISPLAY_NAME);
        User actual = dataJpaUserRepository.findByDisplayName(
                USER_REPOSITORY_DISPLAY_NAME_TO_FIND_BY_DISPLAY_NAME);
        LOGGER.info(String.format("Actual=%s", actual), actual);
        assertEquals(
                String.format("FindByDisplayName test failed:" + lineSeparator() + " expected=%s" +
                                lineSeparator() + " actual= %s",
                        USER_REPOSITORY_CUSTOM_TEST_DATA, actual),
                USER_REPOSITORY_CUSTOM_TEST_DATA,
                actual);
    }
}