package data;

import com.meteoradesigner.model.Task;
import com.meteoradesigner.model.TaskMetric;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static com.meteoradesigner.model.InternalExecutionState.INTERNAL_EXECUTION_STATE_SERIAL;
import static com.meteoradesigner.model.InternalExecutionState.INTERNAL_EXECUTION_STATE_SERIAL_STRICT;
import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_ACTIVE;
import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_FROZEN;
import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY;
import static data.DataJpaTaskContextRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_1;
import static data.DataJpaTaskContextRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_2;
import static data.DataJpaTaskContextRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_3;
import static data.DataJpaTaskContextRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_4;
import static data.DataJpaTaskPortfolioTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_1;
import static data.DataJpaTaskPortfolioTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_3;
import static data.DataJpaUserRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_USER_1;
import static data.DataJpaUserRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2;

public class DataJpaTaskRepositoryTestData extends GenericDataJpaRepositoryTestData<Task> {
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_1;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_2;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_3;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_4;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_5;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_6;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_7;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_8;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_9;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_10;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_11;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_12;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_13;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_14;
    public static Task CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_15;

    private static final TaskMetric CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_15_METRIC;

    //TODO add more saveOneTest logic, for example initialize all the fields
    static {
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_15_METRIC = new TaskMetric("initializedBySqlScriptUser2Task15TaskMetricDescription", 33.3, 18.64, 14.0);
        //constructs saveOneTest data for common tests
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_1 = new Task(1, "initializedBySqlScriptUser1Task1WithContext1And2FromPortfolio1DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_1, "initializedBySqlScriptTask1Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_1, null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_1, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_2)), null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_2 = new Task(2, "initializedBySqlScriptUser1Task2WithContext1And2FromPortfolio1DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_1, "initializedBySqlScriptTask2Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_1, null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_1, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_2)), null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_3 = new Task(3, "initializedBySqlScriptUser2Task3WithContext3And4FromPortfolio3DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask3Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_3, null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_3, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_4)), null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_4 = new Task(4, "initializedBySqlScriptUser2Task4WithContext3And4DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask4Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_3, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_CONTEXT_4)), null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_5 = new Task(5, "initializedBySqlScriptUser2Task5WithExternalTask6And7WithInternalTask8And9DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask5Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_6, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_7)), new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_8, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_9)), null, null, null, null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_6 = new Task(6, "initializedBySqlScriptUser2Task6WithInternalTask5DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask6Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_5)), null, null, null, null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_7 = new Task(7, "initializedBySqlScriptUser2Task7WithInternalTask5DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask7Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_5)), null, null, null, null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_8 = new Task(8, "initializedBySqlScriptUser2Task8WithExternalTask5DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask8Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_5)), null, null, null, null, null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_9 = new Task(9, "initializedBySqlScriptUser2Task9WithExternalTask5DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask9Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_5)), null, null, null, null, null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_10 = new Task(10, "initializedBySqlScriptUser2Task10BlockingTheTask11And12BlockedByTheTask13And14DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask10Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_13, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_14)), new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_11, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_12)), null, null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL_STRICT);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_11 = new Task(11, "initializedBySqlScriptUser2Task11BlockedByTheTask10DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask11Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_10)), null, null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL_STRICT);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_12 = new Task(12, "initializedBySqlScriptUser2Task12BlockedByTheTask10DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask12Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_10)), null, null, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL_STRICT);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_13 = new Task(13, "initializedBySqlScriptUser2Task13BlockingTheTask10DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask13Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_10)), null, null, null, SELF_COMPLETION_STATE_FROZEN, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_14 = new Task(14, "initializedBySqlScriptUser2Task14BlockingTheTask10WithRelativesUnlockedByTheTask15DisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2, "initializedBySqlScriptTask14Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, null, null, new HashSet<>(Arrays.asList(CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_10)), null, null, null, SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY, null, INTERNAL_EXECUTION_STATE_SERIAL);
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_15 = new Task(15, "initializedBySqlScriptUser2Task15UnlockingTheTask14RelativesWithTaskMetricDisplayName", CONSTRUCTED_BY_H2SQL_SCRIPT_USER_1, "initializedBySqlScriptTask15Description", LocalDateTime.of(2018, 2, 23, 21, 18, 33, 064000000), LocalDateTime.of(2018, 3, 1, 21, 18, 33, 064000000), null, null, null, null, null, null, null, null, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_15_METRIC, SELF_COMPLETION_STATE_ACTIVE, null, INTERNAL_EXECUTION_STATE_SERIAL);
    }

    @Override
    public Collection<Task[]> getSaveOneTestData() {
        return null;
    }

    @Override
    public Collection<Task[]> getFindOneTestData() {
        return null;
    }

    @Override
    public Collection<Task[]> getDeleteOneTestData() {
        return null;
    }

    @Override
    public List<Task> getFindAllTestData() {
        return null;
    }
}
