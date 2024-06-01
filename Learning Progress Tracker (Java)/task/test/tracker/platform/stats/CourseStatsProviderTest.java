package tracker.platform.stats;

import org.junit.jupiter.api.Test;
import tracker.platform.CourseName;
import tracker.platform.Credentials;
import tracker.platform.LearningPlatform;
import tracker.platform.Student;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseStatsProviderTest {

    @Test
    void getStatsWithoutStudents() {
        LearningPlatform learningPlatform = new LearningPlatform();
        CourseStatsProvider courseStatsProvider = new CourseStatsProvider(learningPlatform);

        CourseStats courseStats = courseStatsProvider.calculateStats();
        CourseStats expected = new CourseStats();

        assertEquals(expected.getMostPopularCourse(), courseStats.getMostPopularCourse());
        assertEquals(expected.getLeastPopularCourse(), courseStats.getLeastPopularCourse());
        assertEquals(expected.getHighestActivityCourse(), courseStats.getHighestActivityCourse());
        assertEquals(expected.getLowestActivityCourse(), courseStats.getLowestActivityCourse());
        assertEquals(expected.getEasiestCourse(), courseStats.getEasiestCourse());
        assertEquals(expected.getHardestCourse(), courseStats.getHardestCourse());
    }

   /* @Test
    void test1() {
        LearningPlatform learningPlatform = new LearningPlatform();
        CourseStatsProvider courseStatsProvider = new CourseStatsProvider(learningPlatform);

        Student student1 = new Student(
                UUID.randomUUID().toString(),
                new Credentials(
                        "Emelia",
                        "Annnora",
                        "address1@mail.com")
        );
        Student student2 = new Student(
                UUID.randomUUID().toString(),
                new Credentials(
                        "Dolley",
                        "Panther",
                        "address2@mail.com")
        );
        Student student3 = new Student(
                UUID.randomUUID().toString(),
                new Credentials(
                        "Carmella",
                        "Campman",
                        "address3@mail.com")
        );
        Student student4 = new Student(
                UUID.randomUUID().toString(),
                new Credentials(
                        "Carmella",
                        "Campman",
                        "address4@mail.com")
        );
        learningPlatform.addStudent(student1);
        learningPlatform.addStudent(student2);
        learningPlatform.addStudent(student3);
        learningPlatform.addStudent(student4);

        student1.addPoints(CourseName.JAVA, 5);
        student1.addPoints(CourseName.DSA, 4);
        student1.addPoints(CourseName.DATABASES, 3);
        student1.addPoints(CourseName.SPRING, 1);

        student2.addPoints(CourseName.JAVA, 5);
        student2.addPoints(CourseName.DSA, 4);
        student2.addPoints(CourseName.DATABASES, 3);
        student2.addPoints(CourseName.SPRING, 1);

        student3.addPoints(CourseName.JAVA, 5);
        student3.addPoints(CourseName.DSA, 4);
        student3.addPoints(CourseName.DATABASES, 3);
        student3.addPoints(CourseName.SPRING, 1);

        student4.addPoints(CourseName.JAVA, 5);
        student4.addPoints(CourseName.DSA, 4);
        student4.addPoints(CourseName.DATABASES, 3);
        student4.addPoints(CourseName.SPRING, 1);

        CourseStats courseStats = courseStatsProvider.calculateStats();
        CourseStats expected = new CourseStats();
        expected.setMostPopularCourse("Java, DSA, Databases, Spring");
        expected.setLeastPopularCourse("n/a");
        expected.setHighestActivityCourse("Java");
        expected.setLowestActivityCourse("Spring");
        expected.setEasiestCourse("Java");
        expected.setHardestCourse("Spring");

        assertEquals(expected.getMostPopularCourse(), courseStats.getMostPopularCourse());
        assertEquals(expected.getLeastPopularCourse(), courseStats.getLeastPopularCourse());
        assertEquals(expected.getHighestActivityCourse(), courseStats.getHighestActivityCourse());
        assertEquals(expected.getLowestActivityCourse(), courseStats.getLowestActivityCourse());
        assertEquals(expected.getEasiestCourse(), courseStats.getEasiestCourse());
        assertEquals(expected.getHardestCourse(), courseStats.getHardestCourse());
    }

    @Test
    void test2() {

    }*/

}