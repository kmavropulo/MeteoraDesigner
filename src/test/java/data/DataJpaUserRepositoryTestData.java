package data;

import com.meteoradesigner.model.User;

import static com.meteoradesigner.model.Role.ROLE_ADMIN;
import static com.meteoradesigner.model.Role.ROLE_USER;

/**
 * Class holder of test data for @code{DataJpaUserRepository}.
 */
public class DataJpaUserRepositoryTestData {
    public static final User USER_TO_SAVE_ONE_FIRST;
    public static final User USER_TO_SAVE_ONE_SECOND;
    public static final User USER_ADMIN_TO_SAVE_ONE_FIRST;
    public static final User USER_ADMIN_TO_SAVE_ONE_SECOND;

    static {
        USER_TO_SAVE_ONE_FIRST = new User(
                "UserToSaveOneFirst",
                "UserToSaveOneFirst@email.com",
                "UserToSaveOneFirstPassword",
                ROLE_USER);
        USER_TO_SAVE_ONE_SECOND = new User(
                "UserToSaveOneSecond",
                "UserToSaveOneSecond@email.com",
                "UserToSaveOneSecondPassword",
                ROLE_USER);
        USER_ADMIN_TO_SAVE_ONE_FIRST = new User(
                "UserAdminToSaveOneFirst",
                "UserAdminToSaveOneFirst@email.com",
                "UserAdminToSaveOneFirstPassword",
                ROLE_ADMIN);
        USER_ADMIN_TO_SAVE_ONE_SECOND = new User(
                "UserAdminToSaveOneSecond",
                "UserAdminToSaveOneSecond@email.com",
                "UserAdminToSaveOneSecondPassword",
                ROLE_ADMIN);
    }
}