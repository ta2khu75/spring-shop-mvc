package dev.ta2khu75.java5assignment.anotations;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private static final String PHONE_NUMBER_PATTERN = "^\\+84\\s?(3[2-9]|5[689]|7[06-9]|8[1-9]|9[0-9])\\d{7}$";
    // private static final String PHONE_NUMBER_PATTERN =
    // "^\\+62\\s?(2[1-9]|8[1-9][0-9])\\d{6,10}$";
    private Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return false;
        }
        return pattern.matcher(phoneNumber).matches();
    }
}
