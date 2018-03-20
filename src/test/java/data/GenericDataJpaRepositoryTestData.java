package data;

import com.meteoradesigner.HasId;
import com.meteoradesigner.model.TaskContext;
import com.meteoradesigner.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

//TODO documentation.
public abstract class GenericDataJpaRepositoryTestData<E extends HasId> {
    public abstract Collection<E[]> getSaveOneTestData();
    public abstract Collection<E[]> getFindOneTestData();
    public abstract Collection<E[]> getDeleteOneTestData();
    public abstract List<E> getFindAllTestData();

    private static final Logger LOGGER = LoggerFactory.getLogger(
            GenericDataJpaRepositoryTestData.class);

    public static <E extends HasId,
            ID extends Serializable> BiConsumer<E[],
            JpaRepository<E, ID>> getSaveOneTestConsumer() {
        final BiConsumer<E[], JpaRepository<E, ID>> consumer;
        consumer = (ar, rep) -> {
            E expected = ar[1];
            E toSave = ar[0];
            LOGGER.info(String.format("Expected, debugging%s", expected));
            LOGGER.info(String.format("toSave, debugging%s", toSave));
            E actual = rep.save(toSave);
            LOGGER.info(String.format("Actual, debugging%s", actual), actual);
            E fromDb = rep.findOne((ID) expected.getId());
            LOGGER.info(String.format("From DB, debugging%s", fromDb), fromDb);
            assertEquals(
                    String.format("SaveOne saveOneTest failed:" + lineSeparator() + " expected=%s" +
                            lineSeparator() + " actual= %s", expected, actual),
                    expected,
                    actual);
        };
        return consumer;
    }

    public static <E extends HasId,
            ID extends Serializable> BiConsumer<E[],
            JpaRepository<E, ID>> getFindOneTestConsumer() {
        final BiConsumer<E[], JpaRepository<E, ID>> consumer;
        consumer = (ar, rep) -> {
            E expected = ar[1];
            E toFind = ar[0];
            LOGGER.info(String.format("Expected=%s", expected), expected);
            LOGGER.info(String.format("toFind=%s", toFind), toFind);
            E actual = rep.findOne((ID) toFind.getId());
            LOGGER.info(String.format("Actual, debugging%s", actual), actual);
            assertEquals(
                    String.format("FindOne saveOneTest failed:" + lineSeparator() + " expected=%s" +
                            lineSeparator() + " actual= %s", expected, actual),
                    expected,
                    actual);
        };
        return consumer;
    }

    /*public static <E extends HasId,
            ID extends Serializable> BiConsumer<E[],
            JpaRepository<E, ID>> getDeleteUnMasteredOneTestConsumer() {
        final BiConsumer<E[], JpaRepository<E, ID>> consumer;
        consumer = (ar, rep) -> {
            E expected = (E) (ar[1]);
            E toDelete = (E) (ar[0]);
            LOGGER.info(String.format("Expected=%s", expected), expected);
            LOGGER.info(String.format("toDelete=%s", toDelete), toDelete);
            rep.delete(toDelete);
            E actual = rep.findOne((ID) toDelete.getId());
            LOGGER.info(String.format("Actual, debugging%s", actual), actual);
            assertEquals(
                    String.format("DeleteOneUnMastered cruTest failed:" + lineSeparator()
                            + "expected=%s" + lineSeparator()
                            + " actual= %s", expected, actual),
                    expected,
                    actual);
        };
        return consumer;
    }*/

    public static <E extends HasId,
            ID extends Serializable> BiConsumer<E[],
            Map<String, JpaRepository<?,ID>>> getDeleteOneTestConsumer() {
        final BiConsumer<E[], Map<String, JpaRepository<?,ID>>> consumer;
        consumer = (ar, repositoryMap) -> {
            E expected = ar[1];
            E toDelete = ar[0];

            JpaRepository<User, Integer> userRepository
                    = (JpaRepository<User, Integer>) repositoryMap.get
                    ("DataJpaUserRepository");

            LOGGER.info(String.format("Expected=%s", expected), expected);
            LOGGER.info(String.format("toDelete=%s", toDelete), toDelete);

            if (toDelete instanceof TaskContext) {

                JpaRepository<TaskContext, Integer> taskContextRepository
                        = (JpaRepository<TaskContext, Integer>) repositoryMap.get
                        ("DataJpaTaskContextRepository");

                //get current instance
                TaskContext currentToDelete;
                currentToDelete = taskContextRepository.findOne(toDelete.getId());

                currentToDelete.getExternalContexts().forEach(c -> {
                    TaskContext external = taskContextRepository.findOne(c.getId());
                    external.getInternalContexts().remove(currentToDelete);
                    taskContextRepository.save(external);
                });

                currentToDelete.getInternalContexts().forEach(c -> {
                    TaskContext internal = taskContextRepository.findOne(c.getId());
                    internal.getExternalContexts().remove(currentToDelete);
                    taskContextRepository.save(internal);
                });

                User owner = userRepository.findOne((currentToDelete).getUser().getId());
                owner.getContexts().remove(currentToDelete);
                userRepository.save(owner);

                TaskContext actual = taskContextRepository.findOne(currentToDelete.getId());
                LOGGER.info(String.format("Actual, debugging%s", actual), actual);
                assertEquals(
                        String.format("DeleteOne failed:" + lineSeparator()
                                        + "expected=%s" + lineSeparator()
                                        + " actual= %s", expected,
                                actual),
                        expected,
                        actual);
            } else if (toDelete instanceof User) {
                userRepository.delete((User) toDelete);
                User actual = userRepository.findOne((toDelete.getId()));
                LOGGER.info(String.format("Actual, debugging%s", actual), actual);
                assertEquals(
                        String.format("DeleteOne cruTest failed:" + lineSeparator()
                                + "expected=%s" + lineSeparator()
                                + " actual= %s", expected, actual),
                        expected,
                        actual);
            }
        };
        return consumer;
    }
}