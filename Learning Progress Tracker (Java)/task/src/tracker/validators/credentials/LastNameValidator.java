package tracker.validators.credentials;

import tracker.validators.base.Validator;

import java.util.regex.Pattern;

public class LastNameValidator implements Validator<String> {

    private static final Pattern LASTNAME_PATTERN = Pattern.compile("[a-zA-Z]+[-']?[a-zA-Z](\\s?[a-zA-Z]+([-']?[a-zA-Z]+)+)*");

    @Override
    public boolean validate(String input) {
        boolean isValid = LASTNAME_PATTERN.matcher(input).matches();
        if (!isValid) {
            System.out.println("Incorrect last name.");
        }
        return isValid;
    }
}
