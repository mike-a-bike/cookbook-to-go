package ch.zweivelo.ctg.repo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Boot application to start up.
 *
 * @author Michael Bieri
 * @since 26.04.16
 */

@SpringBootApplication
public class RepositoryApplication {

    public static void main(String[] arguments) {
        SpringApplication.run(RepositoryApplication.class, arguments);
    }

}
