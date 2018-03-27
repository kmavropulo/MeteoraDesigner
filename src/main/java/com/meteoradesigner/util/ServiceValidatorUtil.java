package com.meteoradesigner.util;


import com.meteoradesigner.util.exception.NotFoundException;

//TODO doc
public class ServiceValidatorUtil {
    public static void validateConditionNotFound(boolean conditionToCheck, String toPrint) {
        if (!conditionToCheck) {
            throw new NotFoundException(toPrint);
        }
    }

    public static<ID> void validateConditionNotFoundWithId(boolean conditionToCheck, ID id) {
        validateConditionNotFound(conditionToCheck, "id=" + id);
    }

    public static <T> T validateNotFound(T objectToCheck, String toPrint) {
        validateConditionNotFound(objectToCheck != null, toPrint);
        return objectToCheck;
    }

    public static <T,ID> T validateNotFoundWithId(T objectToCheck, ID id) {
        return validateNotFound(objectToCheck, "id=" + id);
    }
}
