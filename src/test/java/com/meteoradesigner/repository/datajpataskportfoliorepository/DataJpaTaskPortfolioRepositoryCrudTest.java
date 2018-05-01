package com.meteoradesigner.repository.datajpataskportfoliorepository;


import com.meteoradesigner.model.TaskPortfolio;
import com.meteoradesigner.repository.DataJpaTaskPortfolioRepository;
import com.meteoradesigner.repository.GenericDataJpaRepositoryCrudTest;
import data.DataJpaTaskPortfolioRepositoryTestData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//TODO documentation
public class DataJpaTaskPortfolioRepositoryCrudTest extends GenericDataJpaRepositoryCrudTest<TaskPortfolio, Integer> {

    @Autowired
    DataJpaTaskPortfolioRepository portfolioRepository;

    @Before
    public void initialize() {
        super.repository = portfolioRepository;
        this.testData = new DataJpaTaskPortfolioRepositoryTestData();
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