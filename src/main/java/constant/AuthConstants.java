package constant;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthConstants {
	public static final String USERNAME_OR_PASWORD_NO_EXIST = "username or email is not already exists!";
	public static final String EMAIL_USER_EXIST = "Email Address already in use!";

}
