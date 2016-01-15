package fr.patouche.slides;

import fr.patouche.slides.dialect.YoungBloodDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class.
 *
 * @author patrick allain.
 */
@Configuration
public class PresentationConfiguration {

    @Bean
    public YoungBloodDialect youngBloodDialect() {
        return new YoungBloodDialect();
    }

}
