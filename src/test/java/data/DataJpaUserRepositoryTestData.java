package data;

import com.meteoradesigner.model.User;

import java.util.Collection;
import java.util.List;

import static com.meteoradesigner.model.Role.ROLE_ADMIN;
import static com.meteoradesigner.model.Role.ROLE_USER;
import static java.util.Arrays.asList;

/**
 * This class @code{DataJpaUserRepositoryTestData} holds of test data for
 *
 * @code{DataJpaUserRepository}.
 */
public class DataJpaUserRepositoryTestData {
    public static final User USER_TO_SAVE_ONE_FIRST;
    public static final User ADMIN_TO_SAVE_ONE_FIRST;
    public static final User USER_ADMIN_TO_SAVE_ONE_SECOND;
    public static final Collection<Object[]> USER_REPOSITORY_SAVE_ONE_PARAMETRIZED_TEST_DATA;

    public static final User USER_TO_FIND_ONE_FIRST_EXPECTED;
    public static final User USER_ADMIN_TO_FIND_ONE_SECOND_EXPECTED;
    public static final Collection<Object[]> USER_REPOSITORY_FIND_ONE_PARAMETRIZED_TEST_DATA;

    public static final User USER_TO_DELETE_ONE_FIRST;
    public static final User USER_ADMIN_TO_DELETE_ONE_SECOND;
    public static final Collection<Object[]> USER_REPOSITORY_DELETE_ONE_PARAMETRIZED_TEST_DATA;

    public static final List<User> USER_REPOSITORY_FIND_ALL_COMMON_TEST_DATA;

    public static final String USER_REPOSITORY_MAIL_TO_FIND_BY_MAIL;

    public static final User USER_REPOSITORY_CUSTOM_TEST_DATA;

    public static final String USER_REPOSITORY_DISPLAY_NAME_TO_FIND_BY_DISPLAY_NAME;

    //TODO add more test logic, for example initialize all the fields
    static {

        USER_TO_SAVE_ONE_FIRST = new User(5,
                "UserToSaveOneFirst",
                "UserToSaveOneFirst@email.com",
                "UserToSaveOneFirstPassword",
                ROLE_USER);

        ADMIN_TO_SAVE_ONE_FIRST = new User(5,
                "AdminToSaveOneFirst",
                "AdminToSaveOneFirst@email.com",
                "AdminToSaveOneFirstPassword",
                ROLE_ADMIN);

        USER_ADMIN_TO_SAVE_ONE_SECOND = new User(5,
                "UserAdminToSaveOneSecond",
                "UserAdminToSaveOneSecond@email.com",
                "UserAdminToSaveOneSecondPassword",
                ROLE_ADMIN, ROLE_USER);

        USER_REPOSITORY_SAVE_ONE_PARAMETRIZED_TEST_DATA = asList(new Object[][]{
                {USER_TO_SAVE_ONE_FIRST, USER_TO_SAVE_ONE_FIRST},
                {ADMIN_TO_SAVE_ONE_FIRST, ADMIN_TO_SAVE_ONE_FIRST},
                {USER_ADMIN_TO_SAVE_ONE_SECOND, USER_ADMIN_TO_SAVE_ONE_SECOND},
        });

        //TODO add more test logic
        USER_TO_FIND_ONE_FIRST_EXPECTED = new User(1,
                "initializedBySqlScriptUser1@email.com",
                "initializedBySqlScriptUser1DisplayName",
                "initializedBySqlScriptUser1Password",
                ROLE_USER);

        USER_ADMIN_TO_FIND_ONE_SECOND_EXPECTED = new User(4,
                "initializedBySqlScriptUserAdmin4@email.com",
                "initializedBySqlScriptUserAdmin4DisplayName",
                "initializedBySqlScriptUserAdmin4Password",
                ROLE_USER, ROLE_ADMIN);

        USER_REPOSITORY_FIND_ONE_PARAMETRIZED_TEST_DATA = asList(new Object[][]{
                {USER_TO_FIND_ONE_FIRST_EXPECTED, USER_TO_FIND_ONE_FIRST_EXPECTED},
                {USER_ADMIN_TO_FIND_ONE_SECOND_EXPECTED, USER_ADMIN_TO_FIND_ONE_SECOND_EXPECTED},
        });

        //TODO add more test logic
        //TODO fix the clone constructor
        //TODO fix the copypast
        USER_TO_DELETE_ONE_FIRST = new User(1,
                "initializedBySqlScriptUser1@email.com",
                "initializedBySqlScriptUser1DisplayName",
                "initializedBySqlScriptUser1Password",
                ROLE_USER);

        USER_ADMIN_TO_DELETE_ONE_SECOND = new User(4,
                "initializedBySqlScriptUserAdmin4@email.com",
                "initializedBySqlScriptUserAdmin4DisplayName",
                "initializedBySqlScriptUserAdmin4Password",
                ROLE_USER, ROLE_ADMIN);

        USER_REPOSITORY_DELETE_ONE_PARAMETRIZED_TEST_DATA = asList(new Object[][]{
                {USER_TO_DELETE_ONE_FIRST, null},
                {USER_ADMIN_TO_DELETE_ONE_SECOND, null},
        });

        USER_REPOSITORY_FIND_ALL_COMMON_TEST_DATA = asList(
                new User(1,
                        "initializedBySqlScriptUser1DisplayName",
                        "initializedBySqlScriptUser1@email.com",
                        "initializedBySqlScriptUser1Password",
                        ROLE_USER),
                new User(2,
                        "initializedBySqlScriptUser2DisplayName",
                        "initializedBySqlScriptUser2@email.com",
                        "initializedBySqlScriptUser2Password",
                        ROLE_USER),
                new User(3,
                        "initializedBySqlScriptAdmin3DisplayName",
                        "initializedBySqlScriptAdmin3@email.com",
                        "initializedBySqlScriptAdmin3Password",
                        ROLE_ADMIN),
                new User(4,
                        "initializedBySqlScriptUserAdmin4DisplayName",
                        "initializedBySqlScriptUserAdmin4@email.com",
                        "initializedBySqlScriptUserAdmin4Password",
                        ROLE_USER, ROLE_ADMIN)
        );

        //TODO fix copypast
        USER_REPOSITORY_MAIL_TO_FIND_BY_MAIL = "initializedBySqlScriptUserAdmin4@email.com";
        USER_REPOSITORY_DISPLAY_NAME_TO_FIND_BY_DISPLAY_NAME =
                "initializedBySqlScriptUserAdmin4DisplayName";
        USER_REPOSITORY_CUSTOM_TEST_DATA = new User(
                4,
                "initializedBySqlScriptUserAdmin4DisplayName",
                "initializedBySqlScriptUserAdmin4@email.com",
                "initializedBySqlScriptUserAdmin4Password",
                ROLE_USER, ROLE_ADMIN);
    }
}