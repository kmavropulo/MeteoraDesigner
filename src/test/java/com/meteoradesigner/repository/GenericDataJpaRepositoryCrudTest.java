package com.meteoradesigner.repository;

import com.meteoradesigner.HasId;
import com.meteoradesigner.repository.config.DataJpaRepositoryTestConfiguration;
import data.GenericDataJpaRepositoryTestData;
import org.springframework.data.jpa.repository.JpaRepository;
import util.GenericTestHelper;

import java.io.Serializable;
import java.util.Map;

//TODO documentation.
public class GenericDataJpaRepositoryCrudTest<E extends HasId, ID extends Serializable> extends
        DataJpaRepositoryTestConfiguration {

    protected JpaRepository<E, ID> repository;
    protected GenericDataJpaRepositoryTestData<E> testData;
    protected Map<String, JpaRepository<?,ID>> repositoryMap;


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