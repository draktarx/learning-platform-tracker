package tracker.validators;

import tracker.platform.CourseName;
import tracker.validators.base.Validator;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddPointsValidator implements Validator<String> {

    private final String POINTS_REGEX = "(\\s+\\d+\\s*){4}";
    private final Pattern POINTS_PATTERN = Pattern.compile(POINTS_REGEX);

    @Override
    public boolean validate(String input) {
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append(UUIDValidator.STUDENT_ID_PATTERN.pattern());
        regexBuilder.append("\\s+\\d+".repeat(CourseName.values().length));
        regexBuilder.append("$");

        Pattern addPointsPattern = Pattern.compile(regexBuilder.toString());
        boolean isValid = addPointsPattern.matcher(input).find();
        if (!isValid) {
            System.out.println("Incorrect points format.");
        }
        return isValid;
    }

    public String extractStudentId(String input) {
        Pattern addPointsPattern = Pattern.compile(UUIDValidator.STUDENT_ID_PATTERN.pattern());
        Matcher matcher = addPointsPattern.matcher(input);

        String studentId = null;
        if (matcher.find())
            studentId = matcher.group();
        return studentId;
    }


    public HashMap<CourseName, Integer> extractPointsToAdd(String input) {
        Matcher matcher = POINTS_PATTERN.matcher(input);

        HashMap<CourseName, Integer> pointsToAdd = new HashMap<>();
        CourseName[] courses = CourseName.values();

        if (!matcher.find())
            System.out.println("Incorrect points format.");
        else {
            String[] points = matcher.group().trim().split("\\s+");
            assert points.length == courses.length : "Incorrect points format.";
            for (int i = 0, valuesLength = courses.length; i < valuesLength; i++) {
                pointsToAdd.put(courses[i], Integer.valueOf(points[i]));
            }
        }
        return pointsToAdd;
    }
}
