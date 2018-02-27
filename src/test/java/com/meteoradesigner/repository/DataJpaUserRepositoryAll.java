package com.meteoradesigner.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Class to run all tests for @code{DataJpaUserRepository}.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataJpaUserRepositoryParametrizedSaveOneTest.class,
        DataJpaUserRepositoryParametrizedGetOneTest.class,
        DataJpaUserRepositoryParametrizedDeleteOneTest.class,
        DataJpaUserRepositoryCommonTest.class,
        DataJpaUserRepositoryCustomTest.class
})
public class DataJpaUserRepositoryAll {
}
