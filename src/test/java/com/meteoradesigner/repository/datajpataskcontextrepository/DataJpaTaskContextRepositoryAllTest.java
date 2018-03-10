package com.meteoradesigner.repository.datajpataskcontextrepository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class @code{DataJpaTaskContextRepositoryAllTest} runs all tests for
 *
 * @code{DataJpaTaskContextRepository}.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataJpaTaskContextRepositoryCommonTest.class,
        DataJpaTaskContextRepositoryParametrizedDeleteOneTest.class,
        DataJpaTaskContextRepositoryCustomTest.class,
        DataJpaTaskContextRepositoryParametrizedFindOneTest.class,
        DataJpaTaskContextRepositoryParametrizedSaveOneTest.class
})
public class DataJpaTaskContextRepositoryAllTest {
}
