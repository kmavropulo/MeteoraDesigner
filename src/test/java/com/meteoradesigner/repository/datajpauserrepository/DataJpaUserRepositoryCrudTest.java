package com.meteoradesigner.repository.datajpauserrepository;

import com.meteoradesigner.model.User;
import com.meteoradesigner.repository.DataJpaUserRepository;
import com.meteoradesigner.repository.GenericDataJpaRepositoryCrudTest;
import data.DataJpaUserRepositoryTestData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//TODO documentation.
public class DataJpaUserRepositoryCrudTest extends
        GenericDataJpaRepositoryCrudTest<User, Integer> {

    @Autowired
    DataJpaUserRepository userRepository;

    @Before
    public void initialize() {
        super.repository = userRepository;
        this.testData = new DataJpaUserRepositoryTestData();
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