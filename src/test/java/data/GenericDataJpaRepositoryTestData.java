package data;

import com.meteoradesigner.HasId;
import com.meteoradesigner.model.Task;
import com.meteoradesigner.model.TaskContext;
import com.meteoradesigner.model.TaskPortfolio;
import com.meteoradesigner.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;

//TODO documentation.
//TODO hard delete tests with multilinked entities
public abstract class GenericDataJpaRepositoryTestData<E extends HasId> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDataJpaRepositoryTestData.class);

    public abstract Collection<E[]> getSaveOneTestData();

    public abstract Collection<E[]> getFindOneTestData();

    public abstract Collection<E[]> getDeleteOneTestData();

    public abstract List<E> getFindAllTestData();

    @SuppressWarnings("unchecked")
    public static <E extends HasId, ID extends Serializable> BiConsumer<E[],
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

    @SuppressWarnings("unchecked")
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

    private static void getDeleteOneTaskTestConsumerSupport(JpaRepository<Task, Integer> taskRepository,
                                                            JpaRepository<User, Integer> userRepository,
                                                            JpaRepository<TaskContext, Integer> taskContextRepository,
                                                            JpaRepository<TaskPortfolio, Integer>
                                                                    taskPortfolioRepository,
                                                            Task currentToDelete,
                                                            Task expected
    ) {

        currentToDelete.getContexts().forEach(task -> {
            TaskContext toUnbind = taskContextRepository.findOne(task.getId());
            toUnbind.getTasks().remove(currentToDelete);
            taskContextRepository.save(toUnbind);
        });
        currentToDelete.setContexts(null);

        TaskPortfolio portfolioToUnbind = currentToDelete.getPortfolio();
        if (portfolioToUnbind != null) {
            portfolioToUnbind.getTasks().remove(currentToDelete);
            taskPortfolioRepository.save(portfolioToUnbind);
            currentToDelete.setPortfolio(null);
        }

        currentToDelete.getExternalTasks().forEach(task -> {
            Task toUnbind = taskRepository.findOne(task.getId());
            toUnbind.getInternalTasks().remove(currentToDelete);
            taskRepository.save(toUnbind);
        });
        currentToDelete.setExternalTasks(null);

        currentToDelete.getInternalTasks().forEach(task -> {
            Task toUnbind = taskRepository.findOne(task.getId());
            toUnbind.getExternalTasks().remove(currentToDelete);
            taskRepository.save(toUnbind);
        });
        currentToDelete.setInternalTasks(null);

        currentToDelete.getTasksBlockedByTheTask().forEach(task -> {
            Task toUnbind = taskRepository.findOne(task.getId());
            toUnbind.getTasksBlockingTheTask().remove(currentToDelete);
            taskRepository.save(toUnbind);
        });
        currentToDelete.setTasksBlockedByTheTask(null);

        currentToDelete.getTasksBlockingTheTask().forEach(task -> {
            Task toUnbind = taskRepository.findOne(task.getId());
            toUnbind.getTasksBlockedByTheTask().remove(currentToDelete);
            taskRepository.save(toUnbind);
        });
        currentToDelete.setTasksBlockingTheTask(null);

        currentToDelete.getTasksUnlockingTheTaskRelatives().forEach(task -> {
            Task toUnbind = taskRepository.findOne(task.getId());
            toUnbind.getTasksWithRelativesUnlockedByTheTask().remove(currentToDelete);
            taskRepository.save(toUnbind);
        });
        currentToDelete.setTasksUnlockingTheTaskRelatives(null);

        currentToDelete.getTasksWithRelativesUnlockedByTheTask().forEach(task -> {
            Task toUnbind = taskRepository.findOne(task.getId());
            toUnbind.getTasksUnlockingTheTaskRelatives().remove(currentToDelete);
            taskRepository.save(toUnbind);
        });
        currentToDelete.setTasksWithRelativesUnlockedByTheTask(null);

        taskRepository.save(currentToDelete);

        User owner = userRepository.findOne(currentToDelete.getUser().getId());
        owner.getTasks().remove(currentToDelete);
        userRepository.save(owner);

        Task actual = taskRepository.findOne(currentToDelete.getId());
        LOGGER.info(String.format("Actual, debugging%s", actual), actual);
        assertEquals(
                String.format("DeleteOne failed:" + lineSeparator()
                                + "expected=%s" + lineSeparator()
                                + " actual= %s", expected,
                        actual),
                expected,
                actual);
    }

    private static void getDeleteOneTaskContextTestConsumerSupport(JpaRepository<TaskContext, Integer>
                                                                           taskContextRepository,
                                                                   JpaRepository<User, Integer> userRepository,
                                                                   JpaRepository<Task, Integer> taskRepository,
                                                                   TaskContext currentToDelete,
                                                                   TaskContext expected
    ) {
        currentToDelete.getExternalContexts().forEach(task -> {
            TaskContext toUnbind = taskContextRepository.findOne(task.getId());
            toUnbind.getInternalContexts().remove(currentToDelete);
            taskContextRepository.save(toUnbind);
        });
        currentToDelete.setExternalContexts(null);

        currentToDelete.getInternalContexts().forEach(task -> {
            TaskContext toUnbind = taskContextRepository.findOne(task.getId());
            toUnbind.getExternalContexts().remove(currentToDelete);
            taskContextRepository.save(toUnbind);
        });
        currentToDelete.setInternalContexts(null);

        currentToDelete.getTasks().forEach(task -> {
            Task toUnbind = taskRepository.findOne(task.getId());
            toUnbind.getContexts().remove(currentToDelete);
            taskRepository.save(toUnbind);
        });
        currentToDelete.setTasks(null);

        taskContextRepository.save(currentToDelete);

        User owner = userRepository.findOne(currentToDelete.getUser().getId());
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
    }

    private static void getDeleteOneTaskPortfolioTestConsumerSupport(JpaRepository<TaskPortfolio, Integer>
                                                                             taskPortfolioRepository,
                                                                     JpaRepository<User, Integer> userRepository,
                                                                     JpaRepository<Task, Integer> taskRepository,
                                                                     TaskPortfolio currentToDelete,
                                                                     TaskPortfolio expected
    ) {
        currentToDelete.getTasks().forEach(task -> {
            Task toUnbind = taskRepository.findOne(task.getId());
            toUnbind.setPortfolio(null);
            taskRepository.save(toUnbind);
        });
        currentToDelete.setTasks(null);

        taskPortfolioRepository.save(currentToDelete);

        User owner = userRepository.findOne((currentToDelete).getUser().getId());
        owner.getPortfolios().remove(currentToDelete);
        userRepository.save(owner);

        TaskPortfolio actual = taskPortfolioRepository.findOne(currentToDelete.getId());
        LOGGER.info(String.format("Actual, debugging%s", actual), actual);
        assertEquals(
                String.format("DeleteOne failed:" + lineSeparator()
                                + "expected=%s" + lineSeparator()
                                + " actual= %s", expected,
                        actual),
                expected,
                actual);
    }

    private static void getDeleteOneUserTestConsumerSupport(JpaRepository<User, Integer> userRepository,
                                                            User currentToDelete,
                                                            User expected
    ) {
        userRepository.delete(currentToDelete);
        User actual = userRepository.findOne((currentToDelete.getId()));
        LOGGER.info(String.format("Actual, debugging%s", actual), actual);
        assertEquals(
                String.format("DeleteOne cruTest failed:" + lineSeparator()
                        + "expected=%s" + lineSeparator()
                        + " actual= %s", expected, actual),
                expected,
                actual);
    }

    @SuppressWarnings("unchecked")
    public static <E extends HasId,
            ID extends Serializable> BiConsumer<E[],
            Map<String, JpaRepository<?, ID>>> getDeleteOneTestConsumer() {
        final BiConsumer<E[], Map<String, JpaRepository<?, ID>>> consumer;
        consumer = (ar, repositoryMap) -> {
            E expected = ar[1];
            E toDelete = ar[0];

            JpaRepository<User, Integer> userRepository
                    = (JpaRepository<User, Integer>) repositoryMap.get
                    ("DataJpaUserRepository");

            JpaRepository<TaskContext, Integer> taskContextRepository
                    = (JpaRepository<TaskContext, Integer>) repositoryMap.get
                    ("DataJpaTaskContextRepository");

            JpaRepository<TaskPortfolio, Integer> taskPortfolioRepository
                    = (JpaRepository<TaskPortfolio, Integer>) repositoryMap.get
                    ("DataJpaTaskPortfolioRepository");

            JpaRepository<Task, Integer> taskRepository
                    = (JpaRepository<Task, Integer>) repositoryMap.get
                    ("DataJpaTaskRepository");


            LOGGER.info(String.format("Expected=%s", expected), expected);
            LOGGER.info(String.format("toDelete=%s", toDelete), toDelete);

            if (toDelete instanceof TaskContext) {

                //get current instance
                TaskContext currentToDelete = taskContextRepository.findOne(toDelete.getId());
                getDeleteOneTaskContextTestConsumerSupport(taskContextRepository, userRepository, taskRepository,
                        currentToDelete, (TaskContext) expected);

            } else if (toDelete instanceof TaskPortfolio) {

                //get current instance
                TaskPortfolio currentToDelete = taskPortfolioRepository.findOne(toDelete.getId());
                getDeleteOneTaskPortfolioTestConsumerSupport(taskPortfolioRepository, userRepository, taskRepository,
                        currentToDelete, (TaskPortfolio) expected);

            } else if (toDelete instanceof Task) {

                //get current instance
                Task currentToDelete = taskRepository.findOne(toDelete.getId());
                getDeleteOneTaskTestConsumerSupport(taskRepository, userRepository, taskContextRepository,
                        taskPortfolioRepository, currentToDelete, (Task) expected);

            } else if (toDelete instanceof User) {

                User currentToDelete = (User) toDelete;
                getDeleteOneUserTestConsumerSupport(userRepository, currentToDelete, (User) expected);
            }
        };
        return consumer;
    }
}