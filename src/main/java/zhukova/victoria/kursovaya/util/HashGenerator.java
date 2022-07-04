package zhukova.victoria.kursovaya.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
    public static BCryptPasswordEncoder getEncoder() {
        return passwordEncoder;
    }
    public static String generatePassHash(String password) {
        return passwordEncoder.encode(password);
    }
}
