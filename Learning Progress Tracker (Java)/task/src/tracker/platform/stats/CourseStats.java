package tracker.platform.stats;

public class CourseStats {

    private String mostPopularCourse;
    private String leastPopularCourse;
    private String highestActivityCourse;
    private String lowestActivityCourse;
    private String easiestCourse;
    private String hardestCourse;

    public CourseStats() {
        this.mostPopularCourse = "n/a";
        this.leastPopularCourse = "n/a";
        this.highestActivityCourse = "n/a";
        this.lowestActivityCourse = "n/a";
        this.easiestCourse = "n/a";
        this.hardestCourse = "n/a";
    }

    public String getMostPopularCourse() {
        return mostPopularCourse;
    }

    public void setMostPopularCourse(String mostPopularCourse) {
        this.mostPopularCourse = mostPopularCourse;
    }

    public String getLeastPopularCourse() {
        return leastPopularCourse;
    }

    public void setLeastPopularCourse(String leastPopularCourse) {
        this.leastPopularCourse = leastPopularCourse;
    }

    public String getHighestActivityCourse() {
        return highestActivityCourse;
    }

    public void setHighestActivityCourse(String highestActivityCourse) {
        this.highestActivityCourse = highestActivityCourse;
    }

    public String getLowestActivityCourse() {
        return lowestActivityCourse;
    }

    public void setLowestActivityCourse(String lowestActivityCourse) {
        this.lowestActivityCourse = lowestActivityCourse;
    }

    public String getEasiestCourse() {
        return easiestCourse;
    }

    public void setEasiestCourse(String easiestCourse) {
        this.easiestCourse = easiestCourse;
    }

    public String getHardestCourse() {
        return hardestCourse;
    }

    public void setHardestCourse(String hardestCourse) {
        this.hardestCourse = hardestCourse;
    }
}
