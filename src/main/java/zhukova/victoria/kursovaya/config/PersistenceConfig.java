package zhukova.victoria.kursovaya.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "zhukova.victoria.kursovaya")
@EnableJpaRepositories(basePackages = "zhukova.victoria.kursovaya")
public class PersistenceConfig {
}
