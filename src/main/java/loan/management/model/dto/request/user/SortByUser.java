package loan.management.model.dto.request.user;

public enum SortByUser {
    ROLES("roles"),
    REQUEST_DATETIME("creationTime");

    private final String field;


    SortByUser(String field) {
        this.field = field;
    }

    public String getField() {
        return this.field;
    }
}
