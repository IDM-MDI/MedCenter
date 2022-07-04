package zhukova.victoria.kursovaya.validator;

import zhukova.victoria.kursovaya.model.dto.AuthenticationDto;
import zhukova.victoria.kursovaya.model.dto.UserDto;
import zhukova.victoria.kursovaya.model.dto.UserInfoDto;

public class UserValidator {
    private static String EMAIL_REGEX = "[a-zA-z0-9]+@[a-z]+\\.[a-z]{2,4}";
    private static String PASSWORD_REGEX = "\\w{6,}";
    public static boolean isAuthenticationValid(AuthenticationDto authentication) {
        return  authentication != null &&
                isEmailValid(authentication.getEmail()) &&
                isPasswordValid(authentication.getPassword());
    }

    public static boolean isEmailValid(String email) {
        return isStringNotEmpty(email) && email.matches(EMAIL_REGEX);
    }
    public static boolean isPasswordValid(String password) {
        return isStringNotEmpty(password) && password.matches(PASSWORD_REGEX);
    }
    public static boolean isUserValid(UserDto user) {
        return user != null &&
                isEmailValid(user.getEmail()) &&
                isPasswordValid(user.getPassword()) &&
                isUserInfoValid(user.getInfo());
    }
    public static boolean isUserInfoValid(UserInfoDto info) {
        return info != null &&
                isStringNotEmpty(info.getName()) &&
                isStringNotEmpty(info.getSurname());
    }
    public static boolean isStringNotEmpty(String string) {
        return string != null && !string.trim().isBlank();
    }
}
