package fr.patouche.slides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

/**
 * Main class for presentation.
 *
 * @author patouche - 13/12/15.
 */

@SpringBootApplication
@Import(PresentationConfiguration.class)
public class PresentationApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PresentationApplication.class);
    }

    /**
     * Entry point of the application.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(PresentationApplication.class, args);
    }

}
