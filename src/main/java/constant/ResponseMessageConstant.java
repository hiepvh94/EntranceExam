package constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseMessageConstant {
    public static final String RETRIEVED_SUCCESSFULLY = "Retrieved successfully";
    public static final String NOT_ENOUGH_PRIVILEGE = "Not enough privilege to access this api";
    public static final String NOT_HAVE_PERMISSION = "Not allowed to access this api";
    public static final String UPDATE_SUCCESSFULLY = "Update success";
    public static final String CREATE_SUCCESSFULLY = "Create success";
    public static final String BAD_REQUEST = "Bad request";
}
