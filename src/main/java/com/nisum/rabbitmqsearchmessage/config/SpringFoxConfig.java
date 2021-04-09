package com.nisum.rabbitmqsearchmessage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api(){
          //  return new  Docket(DocumentationType.SWAGGER_2).select().build();
        return new  Docket(DocumentationType.SWAGGER_2);
    }


}
