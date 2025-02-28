package com.desafio.api.quc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    /** 
     * Retorna um Docket de documentacao da API para swagger.
     * 
     * @return Docket
     */
    @Bean
    public Docket productApi() {
      return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.desafio.api.quc"))
          .build()
          .apiInfo(metaData());
    }
  
    /** 
     * Retorna as informacoes da API para a instancia do Docket.
     * 
     * @return ApiInfo
     */
    private ApiInfo metaData() {
      return new ApiInfoBuilder()
          .title("Quero Um Carro API")
          .description("\"Quero Um Carro [QUC] é um sistema de aluguel de carros usado como exemplo para implementação da stack react, springboot e mongodb.\"")
          .version("1.0.0")
          .build();
    }
  
}
