package com.meteoradesigner.repository.datajpataskrepository;

import com.meteoradesigner.model.Task;
import com.meteoradesigner.repository.DataJpaTaskRepository;
import com.meteoradesigner.repository.GenericDataJpaRepositoryCrudTest;
import data.DataJpaTaskRepositoryTestData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//TODO documentation.
public class DataJpaTaskRepositoryCrudTest extends
        GenericDataJpaRepositoryCrudTest<Task, Integer> {

    @Autowired
    DataJpaTaskRepository taskRepository;

    @Before
    public void initialize() {
        super.repository = taskRepository;
        this.testData = new DataJpaTaskRepositoryTestData();
        setTestCommon();
    }

    @Test
    @Override
    public void saveOneTest() {
        super.saveOneTest();
    }

    @Test
    @Override
    public void findOneTest() {
        super.findOneTest();
    }

    @Test
    @Override
    public void deleteOneTest() {
        super.deleteOneTest();
    }

    @Test
    @Override
    public void findAllTest() {
        super.findAllTest();
    }
}