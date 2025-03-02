package br.com.fcamara.parkingproject.config.swagger;

import br.com.fcamara.parkingproject.model.Company;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public Docket parkingAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.fcamara.parkingproject"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(Company.class)
                .globalOperationParameters(Arrays.asList(
                        new ParameterBuilder()
                        .name("Authorization")
                        .description("Header para token JWT")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build()
                ));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Teste Estacionamento.")
                .description("API REST para gerenciar um estacionamento de carros e motos.")
                .version("V1.0").build();
    }

}
