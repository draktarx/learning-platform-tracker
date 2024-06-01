package tracker.validators.credentials;

import tracker.platform.Credentials;
import tracker.input.CredentialsState;

public class StudentCredentialsValidator {

    private static final NameValidator nameValidator = new NameValidator();
    private static final LastNameValidator lastNameValidator = new LastNameValidator();
    private static final EmailValidator emailValidator = new EmailValidator();

    public static boolean validateStudentCredentials(String name, String lastName, String email) {
        return nameValidator.validate(name) &&
                lastNameValidator.validate(lastName) &&
                emailValidator.validate(email);
    }

    public static CredentialsState validateBeforeBeingParsed(String userCredentialsInput) {
        String[] tokens = userCredentialsInput.trim().split("\\s+");

        if (tokens.length == 1 && "back".equalsIgnoreCase(tokens[0])) {
            return CredentialsState.BACK;
        }

        if (tokens.length < 3) {
            return CredentialsState.NOT_VALID;
        }

        return CredentialsState.VALID;
    }

    public static Credentials extractCredentials(String userCredentialsInput) {
        String[] tokens = userCredentialsInput.strip().split("\\s");

        // El primer token es el nombre
        String name = tokens[0];

        // El último token es el correo electrónico
        String email = tokens[tokens.length - 1];

        // Construir el lastName uniendo los tokens entre el nombre y el correo electrónico
        StringBuilder lastNameBuilder = new StringBuilder();
        for (int i = 1; i < tokens.length - 1; i++) {
            if (!lastNameBuilder.isEmpty()) {
                lastNameBuilder.append(" ");
            }
            lastNameBuilder.append(tokens[i]);
        }
        String lastName = lastNameBuilder.toString();

        if (StudentCredentialsValidator.validateStudentCredentials(name, lastName, email)) {
            return new Credentials(name, lastName, email);
        }

        return null;
    }
}
