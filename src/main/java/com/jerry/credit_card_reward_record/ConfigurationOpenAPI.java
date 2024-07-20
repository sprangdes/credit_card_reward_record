package com.jerry.credit_card_reward_record;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationOpenAPI {


    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().info(new Info()
                .title("Credit Card Reward Record")
                .version("version 1")
                .description("")
                .contact(new io.swagger.v3.oas.models.info.Contact().name("sprangdes").url("https://github.com/sprangdes").email("a89432751@gmail.com"))
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
