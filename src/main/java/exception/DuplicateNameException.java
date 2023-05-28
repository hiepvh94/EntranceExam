package exception;

public class DuplicateNameException extends RuntimeException {
    public DuplicateNameException(Class<?> clazz, String name) {
        super(String.format("Entity %s with name %s has been duplicated", clazz.getSimpleName(), name));
    }
}
