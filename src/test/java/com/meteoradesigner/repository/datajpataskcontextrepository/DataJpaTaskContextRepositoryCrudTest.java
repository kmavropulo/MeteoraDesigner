package com.meteoradesigner.repository.datajpataskcontextrepository;

import com.meteoradesigner.model.TaskContext;
import com.meteoradesigner.repository.DataJpaTaskContextRepository;
import com.meteoradesigner.repository.DataJpaUserRepository;
import com.meteoradesigner.repository.GenericDataJpaRepositoryCrudTest;
import data.DataJpaTaskContextRepositoryTestData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;

//TODO documentation.
public class DataJpaTaskContextRepositoryCrudTest extends
        GenericDataJpaRepositoryCrudTest<TaskContext, Integer> {

    @Autowired
    DataJpaTaskContextRepository taskContextRepository;

    @Autowired
    DataJpaUserRepository userRepository;

    @Before
    public void initialize() {
        super.repository = taskContextRepository;
        this.testData = new DataJpaTaskContextRepositoryTestData();
        this.repositoryMap = new HashMap<String, JpaRepository<?,Integer>>() {{
            put("DataJpaUserRepository", userRepository);
            put("DataJpaTaskContextRepository", taskContextRepository);
        }};
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