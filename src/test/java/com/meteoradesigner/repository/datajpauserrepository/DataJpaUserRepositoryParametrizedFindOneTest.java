package com.meteoradesigner.repository.datajpauserrepository;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.model.User;
import com.meteoradesigner.repository.DataJpaUserRepository;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;
import util.GenericTestHelper;
import java.util.Collection;
import static data.DataJpaUserRepositoryTestData.USER_REPOSITORY_FIND_ONE_PARAMETRIZED_TEST_DATA;

/**
 * This class @code{DataJpaUserRepositoryParametrizedFindOneTest} runs parametrized tests to
 * test @code{DataJpaUserRepository}'s find one method.
 */
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(Parameterized.class)
public class DataJpaUserRepositoryParametrizedFindOneTest {

    @Autowired
    private DataJpaUserRepository dataJpaUserRepository;

    private User toFind;
    private User expected;

    //using for parametrized test
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    //only public for parametrized test

    /**
     * Constructs instance for parametrized testing.
     */
    public DataJpaUserRepositoryParametrizedFindOneTest(User toFind, User expected) {
        this.toFind = toFind;
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
        return USER_REPOSITORY_FIND_ONE_PARAMETRIZED_TEST_DATA;
    }

    /**
     * Tests @code{findOne} method.
     */
    @Test
    public void findOne() {
        new GenericTestHelper<User>().findOne(dataJpaUserRepository,toFind,expected);
    }
}