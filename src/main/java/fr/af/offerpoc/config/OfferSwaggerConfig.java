package fr.af.offerpoc.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Config
 *
 * @Author TGI
 * @Date 24/03/2022
 */
@Configuration
public class OfferSwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Offer Technical Test").description(
                        "Offer Technical Test."));
    }


}
