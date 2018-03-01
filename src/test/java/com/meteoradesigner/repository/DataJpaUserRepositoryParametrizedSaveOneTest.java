package com.meteoradesigner.repository;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.model.User;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

import static data.DataJpaUserRepositoryTestData.ADMIN_TO_SAVE_ONE_FIRST;
import static data.DataJpaUserRepositoryTestData.ADMIN_TO_SAVE_ONE_FIRST_EXPECTED;
import static data.DataJpaUserRepositoryTestData.ADMIN_TO_SAVE_ONE_SECOND;
import static data.DataJpaUserRepositoryTestData.ADMIN_TO_SAVE_ONE_SECOND_EXPECTED;
import static data.DataJpaUserRepositoryTestData.USER_TO_SAVE_ONE_FIRST;
import static data.DataJpaUserRepositoryTestData.USER_TO_SAVE_ONE_FIRST_EXPECTED;
import static data.DataJpaUserRepositoryTestData.USER_TO_SAVE_ONE_SECOND;
import static data.DataJpaUserRepositoryTestData.USER_TO_SAVE_ONE_SECOND_EXPECTED;
import static java.lang.System.lineSeparator;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Class to run parametrized tests to test @code{DataJpaUserRepository}'s save one method.
 */

@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(Parameterized.class)
public class DataJpaUserRepositoryParametrizedSaveOneTest {
    private static final Logger LOGGER = LoggerFactory.getLogger
            (DataJpaUserRepositoryParametrizedSaveOneTest.class);

    //TODO @Repository for DataJPA?
    @Autowired
    private DataJpaUserRepository dataJpaUserRepository;

    private User toSave;
    private User expected;

    //using for parametrized test
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    //only public for parametrized test

    /**
     * Constructor for parametrized testing.
     */
    public DataJpaUserRepositoryParametrizedSaveOneTest(User toSave, User expected) {
        this.toSave = toSave;
        this.expected = expected;
    }

    //only public for parametrized

    /**
     * Set the parametrized data.
     *
     * @return @code{Collection<Object[]>} of parametrized data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> setParametrizedData() {
        return asList(new Object[][]{
                {USER_TO_SAVE_ONE_FIRST, USER_TO_SAVE_ONE_FIRST_EXPECTED},
                {USER_TO_SAVE_ONE_SECOND, USER_TO_SAVE_ONE_SECOND_EXPECTED},
                {ADMIN_TO_SAVE_ONE_FIRST, ADMIN_TO_SAVE_ONE_FIRST_EXPECTED},
                {ADMIN_TO_SAVE_ONE_SECOND, ADMIN_TO_SAVE_ONE_SECOND_EXPECTED},
        });
    }

    /**
     * Test the save method, persisting one entity per commit.
     */
    @Test
    public void saveOne() {
        User actual = dataJpaUserRepository.save(toSave);
        LOGGER.info("Actual, debugging",actual);
        User byGet = dataJpaUserRepository.getOne(actual.getId());
        LOGGER.info("ByGet, debugging",byGet);
        assertEquals(
                String.format("SaveOne test failed:" + lineSeparator() + " expected=%s" +
                        lineSeparator() + " actual= %s", expected, actual),
                expected,
                actual);
    }
}