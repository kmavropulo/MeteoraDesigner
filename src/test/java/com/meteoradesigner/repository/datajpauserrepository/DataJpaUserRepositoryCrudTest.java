package com.meteoradesigner.repository.datajpauserrepository;

import com.meteoradesigner.model.User;
import com.meteoradesigner.repository.DataJpaTaskContextRepository;
import com.meteoradesigner.repository.DataJpaUserRepository;
import com.meteoradesigner.repository.GenericDataJpaRepositoryCrudTest;
import data.DataJpaUserRepositoryTestData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;

//TODO documentation.
public class DataJpaUserRepositoryCrudTest extends
        GenericDataJpaRepositoryCrudTest<User, Integer> {

    @Autowired
    DataJpaUserRepository userRepository;

    @Autowired
    DataJpaTaskContextRepository taskContextRepository;

    @Before
    public void initialize() {
        super.repository = userRepository;
        this.testData = new DataJpaUserRepositoryTestData();
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