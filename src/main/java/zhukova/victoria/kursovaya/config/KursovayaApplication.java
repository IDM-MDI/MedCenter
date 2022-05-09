package zhukova.victoria.kursovaya.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "zhukova.victoria.kursovaya")
public class KursovayaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KursovayaApplication.class, args);
    }

}
