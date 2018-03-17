package com.meteoradesigner.repository.config;

import com.meteoradesigner.config.AppConfig;
import com.meteoradesigner.model.AbstractBaseEntity;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.BiConsumer;

import static org.junit.Assert.assertEquals;

/**
 * This class @code{DataJpaRepositoryTestConfiguration} runs parametrized tests to
 * saveOneTest @code{DataJpaTaskContextRepository}'s save one method.
 */
//TODO documentation.
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public abstract class DataJpaRepositoryTestConfiguration {
}