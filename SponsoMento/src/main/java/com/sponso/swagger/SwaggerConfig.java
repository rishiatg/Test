/**
 * 
 */
package com.sponso.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author a632972
 *
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.sponso"})
public class SwaggerConfig {

    /**
     * @return Docket
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
	        .select()                                  
	        .apis(RequestHandlerSelectors.any())              
	        .paths(PathSelectors.any())                          
	        .build()
	        .apiInfo(apiInfo());
    }//configuring the API & API Endpoint url 

    /**
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
	        .title("Application Name")
	        .description("Application Api")
	        .version("1.0.0")
            .build();
    }//configuring the API info of swagger 

}