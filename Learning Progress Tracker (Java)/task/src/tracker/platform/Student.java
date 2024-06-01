package tracker.platform;

import java.util.HashMap;
import java.util.Map;

import static tracker.platform.CourseName.*;
import static tracker.platform.CourseName.SPRING;

public class Student {
    private final Credentials credentials;
    private String id;
    private Map<CourseName, Integer> courseMapPoints;
    private Map<CourseName, Completion> completed;

    public Student(String id, Credentials credentials) {
        this.id = id;
        this.credentials = credentials;
        this.courseMapPoints = new HashMap<>();
        this.completed = new HashMap<>();
        for (CourseName courseName : values()) {
            completed.put(courseName, new Completion(false, false));
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String fullname() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(getCredentials().name()).append(" ").append(getCredentials().lastname()).toString();
    }


    public void addPoints(CourseName course, int points) {
        this.courseMapPoints.merge(course, points, Integer::sum);
    }

    public int getPoints(CourseName course) {
        return this.courseMapPoints.getOrDefault(course, 0);
    }

    public boolean isEnrolled(CourseName course) {
        return this.courseMapPoints.getOrDefault(course, 0) > 0;
    }

    public void showPoints() {
        System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n",
                this.getId(),
                getPoints(JAVA),
                getPoints(DSA),
                getPoints(DATABASES),
                getPoints(SPRING)
        );
    }

    public String getId() {
        return id;
    }

    public double getCompletionPercentage(CourseName courseName) {
        int completionPoints;
        switch (courseName) {
            case JAVA -> completionPoints = 600;
            case DSA -> completionPoints = 400;
            case DATABASES -> completionPoints = 480;
            case SPRING -> completionPoints = 550;
            default -> throw new IllegalArgumentException("Unknown course name: " + courseName);
        }
        double completionPercentage = (double) getPoints(courseName) / completionPoints * 100;
        if (completionPercentage >= 100) {
            courseMapPoints.put(courseName, 100);
            completed.put(courseName, new Completion(true, false));
        }
        return completionPercentage;
    }

    public Map<CourseName, Completion> getCompletedCourses() {
        for (CourseName courseName : values()) {
            getCompletionPercentage(courseName);
        }
        return completed;
    }
}
