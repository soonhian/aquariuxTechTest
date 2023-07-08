package com.AquariuxTechTest.Aquariux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
@ComponentScan({"com.AquariuxTechTest.Aquariux"})
@EntityScan(basePackages = "com.AquariuxTechTest.Aquariux.*")
//@EnableJpaRepositories("your.repository.packages")
public class AquariuxApplication {

	public static void main(String[] args) {
		SpringApplication.run(AquariuxApplication.class, args);
	}

}
