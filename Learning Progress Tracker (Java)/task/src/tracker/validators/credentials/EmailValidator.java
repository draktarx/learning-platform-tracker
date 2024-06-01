package tracker.validators.credentials;

import tracker.validators.base.Validator;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_%+-.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");

    @Override
    public boolean validate(String email) {
        boolean isValid = EMAIL_PATTERN.matcher(email).matches();
        if (!isValid) {
            System.out.println("Incorrect email.");
        }
        return isValid;
    }
}
