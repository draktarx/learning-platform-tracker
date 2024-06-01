package tracker.validators.credentials;

import tracker.validators.base.Validator;

import java.util.regex.Pattern;

public class NameValidator implements Validator<String> {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+[-']?[a-zA-Z]+$");

    @Override
    public boolean validate(String input) {
        boolean isValid = NAME_PATTERN.matcher(input).matches();
        if (!isValid) {
            System.out.println("Incorrect first name.");
        }
        return isValid;
    }
}
