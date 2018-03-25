package com.meteoradesigner.repository;

import com.meteoradesigner.HasId;
import com.meteoradesigner.repository.config.DataJpaRepositoryTestConfiguration;
import data.GenericDataJpaRepositoryTestData;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import util.GenericTestHelper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static data.DataJpaTaskRepositoryTestData.CONSTRUCTED_PROGRAMMATICALLY_TASK_TEST_DATA;

@Ignore
//TODO documentation.
public class GenericDataJpaRepositoryCrudTest<E extends HasId, ID extends Serializable> extends
        DataJpaRepositoryTestConfiguration {

    @Autowired
    private  DataJpaTaskPortfolioRepository portfolioRepository;

    @Autowired
    private DataJpaTaskRepository taskRepository;

    @Autowired
    private DataJpaTaskContextRepository taskContextRepository;

    @Autowired
    private DataJpaUserRepository userRepository;

    protected JpaRepository<E, ID> repository;
    protected GenericDataJpaRepositoryTestData<E> testData;
    protected Map<String, JpaRepository<?,Integer>> repositoryMap;

    protected void setTestCommon() {
        repositoryMap = new HashMap<String, JpaRepository<?,Integer>>() {{
            put("DataJpaUserRepository", userRepository);
            put("DataJpaTaskContextRepository", taskContextRepository);
            put("DataJpaTaskRepository", taskRepository);
            put("DataJpaTaskPortfolioRepository", portfolioRepository);
        }};

        //constructed programmatically test data sets here
        taskRepository.save(CONSTRUCTED_PROGRAMMATICALLY_TASK_TEST_DATA);
    }

    public void saveOneTest() {
        GenericTestHelper.cruTest(
                testData.getSaveOneTestData(),
                repository,
                GenericDataJpaRepositoryTestData.getSaveOneTestConsumer()
        );
    }

    public void findOneTest() {
        GenericTestHelper.cruTest(
                testData.getFindOneTestData(),
                repository,
                GenericDataJpaRepositoryTestData.getFindOneTestConsumer()
        );
    }

    public void deleteOneTest() {
        GenericTestHelper.deleteTest(
                testData.getDeleteOneTestData(),
                repositoryMap,
                GenericDataJpaRepositoryTestData.getDeleteOneTestConsumer()
        );
    }

    public void findAllTest() {
        GenericTestHelper.findAll(
                testData.getFindAllTestData(),
                repository
        );
    }
}