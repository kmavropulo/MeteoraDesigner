package com.meteoradesigner.repository.datajpauserrepository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class @code{DataJpaUserRepositoryAllTest} runs all tests for @code{DataJpaUserRepository}.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataJpaUserRepositoryParametrizedSaveOneTest.class,
        DataJpaUserRepositoryParametrizedDeleteOneTest.class,
        DataJpaUserRepositoryParametrizedDeleteOneTest.class,
        DataJpaUserRepositoryCommonTest.class,
        DataJpaUserRepositoryCustomTest.class
})
public class DataJpaUserRepositoryAllTest {
}
