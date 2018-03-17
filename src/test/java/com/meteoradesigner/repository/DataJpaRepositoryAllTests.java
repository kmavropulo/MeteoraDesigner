package com.meteoradesigner.repository;

import com.meteoradesigner.repository.datajpataskcontextrepository
        .DataJpaTaskContextRepositoryAllTest;
import com.meteoradesigner.repository.datajpauserrepository.DataJpaUserRepositoryAllTest;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//TODO documentation.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataJpaTaskContextRepositoryAllTest.class,
        DataJpaUserRepositoryAllTest.class
})
public class DataJpaRepositoryAllTests {
}