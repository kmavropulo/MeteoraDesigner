package com.meteoradesigner.repository.datajpataskcontextrepository;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.model.TaskContext;
import com.meteoradesigner.model.User;
import com.meteoradesigner.repository.DataJpaTaskContextRepository;
import com.meteoradesigner.repository.DataJpaUserRepository;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

import static data.DataJpaTaskContextRepositoryTestData
        .TASK_CONTEXT_REPOSITORY_DELETE_ONE_PARAMETRIZED_TEST_DATA;
import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

/**
 * This class @code{DataJpaTaskContextRepositoryParametrizedDeleteOneTest} runs parametrized
 * tests to test @code{DataJpaTaskContextRepository}'s delete one method.
 */
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(Parameterized.class)
public class DataJpaTaskContextRepositoryParametrizedDeleteOneTest {

    //TODO @Repository for DataJPA?
    @Autowired
    private DataJpaTaskContextRepository dataJpaTaskContextRepository;

    @Autowired
    private DataJpaUserRepository dataJpaUserRepository;

    private TaskContext toDelete;
    private TaskContext expected;

    //using for parametrized test
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    private static final Logger LOGGER = LoggerFactory.getLogger(
            DataJpaTaskContextRepositoryParametrizedDeleteOneTest.class);

    //only public for parametrized test

    /**
     * Constructs instance for parametrized testing.
     */
    public DataJpaTaskContextRepositoryParametrizedDeleteOneTest(TaskContext toDelete,
                                                                 TaskContext expected) {
        this.toDelete = toDelete;
        this.expected = expected;
    }

    //only public for parametrized

    /**
     * Sets the parametrized data.
     *
     * @return @code{Collection<Object[]>} of parametrized data.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> setParametrizedData() {
        return TASK_CONTEXT_REPOSITORY_DELETE_ONE_PARAMETRIZED_TEST_DATA;
    }

    /**
     * Tests the deleting the @code{TaskContext} entity by updating it's associations, later this
     * code will be refactored and using in services package.
     */
    @Test
    public void deleteOne() {
        LOGGER.info(String.format("toDelete=%s", toDelete), toDelete);

        //get current instance
        toDelete = dataJpaTaskContextRepository.findOne(toDelete.getId());

        //unbinding it from other contexts
        toDelete.getExternalContexts().forEach(c -> {
            TaskContext external = dataJpaTaskContextRepository.findOne(c.getId());
            external.getInternalContexts().remove(toDelete);
            dataJpaTaskContextRepository.save(external);
        });

        toDelete.getInternalContexts().forEach(c -> {
            TaskContext internal = dataJpaTaskContextRepository.findOne(c.getId());
            internal.getExternalContexts().remove(toDelete);
            dataJpaTaskContextRepository.save(internal);
        });

        //unbinding from users
        User owner = dataJpaUserRepository.findOne((toDelete).getUser().getId());
        owner.getContexts().remove(toDelete);
        dataJpaUserRepository.save(owner);

        //checking
        TaskContext actual = dataJpaTaskContextRepository.findOne(toDelete.getId());
        LOGGER.info(String.format("Actual, debugging%s", actual), actual);
        assertEquals(
                String.format("DeleteOne test failed:" + lineSeparator() + " expected=%s" +
                        lineSeparator() + " actual= %s", expected, actual),
                expected,
                actual);
    }
}