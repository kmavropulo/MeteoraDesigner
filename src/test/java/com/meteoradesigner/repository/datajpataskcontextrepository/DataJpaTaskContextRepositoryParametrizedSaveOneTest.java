package com.meteoradesigner.repository.datajpataskcontextrepository;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.model.TaskContext;
import com.meteoradesigner.repository.DataJpaTaskContextRepository;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;
import util.GenericTestHelper;

import java.util.Collection;

import static data.DataJpaTaskContextRepositoryTestData
        .TASK_CONTEXT_REPOSITORY_SAVE_ONE_PARAMETRIZED_TEST_DATA;

/**
 * This class @code{DataJpaTaskContextRepositoryParametrizedSaveOneTest} runs parametrized tests to
 * test @code{DataJpaTaskContextRepository}'s save one method.
 */
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(Parameterized.class)
public class DataJpaTaskContextRepositoryParametrizedSaveOneTest {

    //TODO @Repository for DataJPA?
    @Autowired
    private DataJpaTaskContextRepository dataJpaTaskContextRepository;

    private TaskContext toSave;
    private TaskContext expected;

    //using for parametrized test
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    //only public for parametrized test

    /**
     * Constructs instance for parametrized testing.
     */
    public DataJpaTaskContextRepositoryParametrizedSaveOneTest(TaskContext toSave, TaskContext
            expected) {
        this.toSave = toSave;
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
        return TASK_CONTEXT_REPOSITORY_SAVE_ONE_PARAMETRIZED_TEST_DATA;
    }

    /**
     * Tests @code{saveOne} method.
     */
    @Test
    public void saveOne() {
        new GenericTestHelper<TaskContext>().saveOne(dataJpaTaskContextRepository, toSave,
                expected);
    }
}