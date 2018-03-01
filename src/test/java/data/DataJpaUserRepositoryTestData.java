package data;

import com.meteoradesigner.model.User;

import static com.meteoradesigner.model.Role.ROLE_ADMIN;
import static com.meteoradesigner.model.Role.ROLE_USER;

/**
 * Class holder of test data for @code{DataJpaUserRepository}.
 */
public class DataJpaUserRepositoryTestData {
    public static final User USER_TO_SAVE_ONE_FIRST;
    public static final User USER_TO_SAVE_ONE_FIRST_EXPECTED;
    public static final User USER_TO_SAVE_ONE_SECOND;
    public static final User USER_TO_SAVE_ONE_SECOND_EXPECTED;
    public static final User ADMIN_TO_SAVE_ONE_FIRST;
    public static final User ADMIN_TO_SAVE_ONE_FIRST_EXPECTED;
    public static final User ADMIN_TO_SAVE_ONE_SECOND;
    public static final User ADMIN_TO_SAVE_ONE_SECOND_EXPECTED;

    static {
        USER_TO_SAVE_ONE_FIRST = new User(
                "UserToSaveOneFirst",
                "UserToSaveOneFirst@email.com",
                "UserToSaveOneFirstPassword",
                ROLE_USER);
        USER_TO_SAVE_ONE_FIRST_EXPECTED = new User(USER_TO_SAVE_ONE_FIRST);
                USER_TO_SAVE_ONE_FIRST_EXPECTED.setId(1);

        USER_TO_SAVE_ONE_SECOND = new User(
                "UserToSaveOneSecond",
                "UserToSaveOneSecond@email.com",
                "UserToSaveOneSecondPassword",
                ROLE_USER);
        USER_TO_SAVE_ONE_SECOND_EXPECTED=new User(USER_TO_SAVE_ONE_SECOND);
        USER_TO_SAVE_ONE_SECOND_EXPECTED.setId(1);

        ADMIN_TO_SAVE_ONE_FIRST = new User(
                "UserAdminToSaveOneFirst",
                "UserAdminToSaveOneFirst@email.com",
                "UserAdminToSaveOneFirstPassword",
                ROLE_ADMIN);
        ADMIN_TO_SAVE_ONE_FIRST_EXPECTED=new User(ADMIN_TO_SAVE_ONE_FIRST);
        ADMIN_TO_SAVE_ONE_FIRST_EXPECTED.setId(1);

        ADMIN_TO_SAVE_ONE_SECOND = new User(
                "UserAdminToSaveOneSecond",
                "UserAdminToSaveOneSecond@email.com",
                "UserAdminToSaveOneSecondPassword",
                ROLE_ADMIN);
        ADMIN_TO_SAVE_ONE_SECOND_EXPECTED=new User(USER_TO_SAVE_ONE_SECOND);
        ADMIN_TO_SAVE_ONE_SECOND_EXPECTED.setId(1);
    }
}