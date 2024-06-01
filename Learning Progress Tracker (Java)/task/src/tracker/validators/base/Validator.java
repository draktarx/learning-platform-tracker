package tracker.validators.base;

public interface Validator<T> {
    boolean validate(T input);
}
