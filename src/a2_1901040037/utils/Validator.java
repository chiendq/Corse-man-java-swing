package a2_1901040037.utils;

import a2_1901040037.exceptions.InvalidValueException;

import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_PATTERN = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";

    private static final String DD_MM_YYYY_PATTERN = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";


    public static boolean validateEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    public static boolean validateDate_DD_MM_YYYY(String dateString){
        return Pattern.matches(DD_MM_YYYY_PATTERN, dateString);
    }
}
