package ru.ecosystem.carsale.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableScheduling
public class CarsaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsaleApplication.class, args);
    }
}
