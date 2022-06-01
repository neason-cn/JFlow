package com.jflow.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The JFlow Application.
 *
 * @author neason
 * @since 0.0.1
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.jflow"
        }
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
