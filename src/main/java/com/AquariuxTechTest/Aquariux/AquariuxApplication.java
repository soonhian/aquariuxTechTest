package com.AquariuxTechTest.Aquariux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@Configuration
@ComponentScan({"com.AquariuxTechTest.Aquariux"})
@EntityScan(basePackages = "com.AquariuxTechTest.Aquariux.*")
//@EnableJpaRepositories("your.repository.packages")
@EnableSwagger2
@EnableWebMvc
//@ConditionalOnProperty(name="app.api.swagger.enable", havingValue = "true", matchIfMissing = false)
//@ConfigurationProperties("app.api")
public class AquariuxApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AquariuxApplication.class, args);
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
//	    registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
//	    registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
//	    registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry
         .addResourceHandler("swagger-ui.html")
         .addResourceLocations("classpath:/META-INF/resources/");

 registry
         .addResourceHandler("/webjars/**")
         .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
