package tracker.validators;

import tracker.validators.base.Validator;

import java.util.regex.Pattern;

public class UUIDValidator implements Validator<String> {

    public static final Pattern STUDENT_ID_PATTERN = Pattern.compile("\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0" +
            "-9a-f]{4" +
            "}-[0-9a-f]{12}\\b");

    @Override
    public boolean validate(String input) {
        boolean isValid = STUDENT_ID_PATTERN.matcher(input).matches();
        if (!isValid) {
            System.out.println("Incorrect first name.");
        }
        return isValid;
    }

}
