package loan.management.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthConstants {
    public static final String USERNAME_OR_PASWORD_NO_EXIST = "username or email is not already exists!";
    public static final String USERNAME_USER_EXIST = "username already in use!";
    public static final String SIGNUP_USER_UNSUCCES = "signup is unsucess";
    public static final String EMAIL_USER_EXIST = "Email Address already in use!";

}
