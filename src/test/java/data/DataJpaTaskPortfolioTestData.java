package data;

import com.meteoradesigner.model.TaskPortfolio;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static data.DataJpaTaskRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_1;
import static data.DataJpaTaskRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_2;
import static data.DataJpaTaskRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_3;
import static data.DataJpaUserRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_USER_1;
import static data.DataJpaUserRepositoryTestData.CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2;
import static java.util.Arrays.asList;

public class DataJpaTaskPortfolioTestData extends GenericDataJpaRepositoryTestData<TaskPortfolio> {

    public static final TaskPortfolio CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_1;
    public static final TaskPortfolio CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_2;
    public static final TaskPortfolio CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_3;
    public static final TaskPortfolio CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_4;

    static {

        //constructs data for common tests
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_1 = new TaskPortfolio(
                1,
                "initializedBySqlScriptUser1Portfolio1DisplayName",
                CONSTRUCTED_BY_H2SQL_SCRIPT_USER_1,
                "initializedBySqlScriptUser1Portfolio1Description",
                new HashSet<>(asList(
                        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_1, CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_2)));
        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_2 = new TaskPortfolio(
                2,
                "initializedBySqlScriptUser1Portfolio2DisplayName",
                CONSTRUCTED_BY_H2SQL_SCRIPT_USER_1,
                "initializedBySqlScriptUser1Portfolio2Description",
                null);

        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_3 = new TaskPortfolio(
                3,
                "initializedBySqlScriptUser2Portfolio3DisplayName",
                CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2,
                "initializedBySqlScriptUser2Portfolio3Description",
                new HashSet<>(asList(
                        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_3)));

        CONSTRUCTED_BY_H2SQL_SCRIPT_TASK_PORTFOLIO_4 = new TaskPortfolio(
                4,
                "initializedBySqlScriptUser2Portfolio4DisplayName",
                CONSTRUCTED_BY_H2SQL_SCRIPT_USER_2,
                "initializedBySqlScriptUser2Portfolio4Description",
                null);
    }

    @Override
    public Collection<TaskPortfolio[]> getSaveOneTestData() {
        return null;
    }

    @Override
    public Collection<TaskPortfolio[]> getFindOneTestData() {
        return null;
    }

    @Override
    public Collection<TaskPortfolio[]> getDeleteOneTestData() {
        return null;
    }

    @Override
    public List<TaskPortfolio> getFindAllTestData() {
        return null;
    }
}
