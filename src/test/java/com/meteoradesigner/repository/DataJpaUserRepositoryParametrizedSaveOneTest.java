package com.meteoradesigner.repository;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.swing.Spring;
import java.util.Collection;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

/**
 * Class to run parametrized tests to test @code{DataJpaUserRepository}'s save one method.
 */

//TODO how to run Parametrized.class +parametrized tests with Spring runner
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class DataJpaUserRepositoryParametrizedSaveOneTest {

    //TODO @Repository for DataJPA?
    @Autowired
    private DataJpaUserRepository dataJpaUserRepository;

    private User toSave;
    private User expected;

    //TODO check different encapsulations

    /**
     * Constructor for parametrized testing.
     */
    DataJpaUserRepositoryParametrizedSaveOneTest(User toSave, User expected) {
        this.toSave = toSave;
        this.expected = expected;
    }

    //TODO check different encapsulations

    /**
     * Set the parametrized data.
     *
     * @return @code{Collection<Object[]>} of parametrized data
     */
    @Parameterized.Parameters
    static Collection<Object[]> setParametrizedData() {

        //TODO to init
        return null;
    }

    /**
     * Test the save method, persisting one entity per commit.
     */
    @Test
    public void saveOne() {
        User actual = dataJpaUserRepository.save(toSave);
        assertEquals(String.format("SaveOne test failed:" + lineSeparator() + " expected=%s" +
                        lineSeparator() + " actual= %s", expected, actual),
                expected,
                actual);
    }
}