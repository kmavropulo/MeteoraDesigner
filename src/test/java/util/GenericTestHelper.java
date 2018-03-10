package util;

import com.meteoradesigner.model.AbstractBaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

//TODO add documentation
public class GenericTestHelper<E extends AbstractBaseEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericTestHelper.class);

    //TODO how to autowire DataJpaUserRepository
    public void saveOne(JpaRepository<E, Integer> jpaRepository, E toSave, E expected) {
        E actual = jpaRepository.save(toSave);
        LOGGER.info(String.format("Actual, debugging%s", actual), actual);
        E fromDb = jpaRepository.findOne(actual.getId());
        LOGGER.info(String.format("From DB, debugging%s", fromDb), fromDb);
        assertEquals(
                String.format("SaveOne test failed:" + lineSeparator() + " expected=%s" +
                        lineSeparator() + " actual= %s", expected, actual),
                expected,
                actual);
    }

    public void findOne(JpaRepository<E, Integer> jpaRepository, E toFind, E expected) {
        LOGGER.info(String.format("toFind=%s", toFind), toFind);
        E actual = jpaRepository.findOne(toFind.getId());
        LOGGER.info(String.format("Actual, debugging=%s", actual), actual);
        assertEquals(
                String.format("FindOne test failed:" + lineSeparator() + " expected=%s" +
                        lineSeparator() + " actual= %s", expected, actual),
                expected,
                actual);
    }

    public void deleteOne(JpaRepository<E, Integer> jpaRepository, E toDelete, E expected) {
        LOGGER.info(String.format("toDelete=%s", toDelete), toDelete);
        jpaRepository.delete(toDelete);
        E actual = jpaRepository.findOne(toDelete.getId());
        LOGGER.info(String.format("Actual, debugging%s", actual), actual);
        assertEquals(
                String.format("DeleteOne test failed:" + lineSeparator() + " expected=%s" +
                        lineSeparator() + " actual= %s", expected, actual),
                expected,
                actual);
    }

    public void findAll(JpaRepository<E, Integer> jpaRepository, List<E> expected) {
        List<E> actual = jpaRepository.findAll();
        LOGGER.info(String.format("Actual=%s", actual), actual);
        assertEquals(
                String.format("FindAll test failed:" + lineSeparator() + " expected=%s" +
                                lineSeparator() + " actual= %s",
                        expected, actual),
                expected,
                actual);
    }
}