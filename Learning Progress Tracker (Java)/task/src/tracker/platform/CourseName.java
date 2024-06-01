package tracker.platform;

public enum CourseName {
    JAVA("Java"),
    DSA("DSA"),
    DATABASES("Databases"),
    SPRING("Spring");

    private final String displayName;

    CourseName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}