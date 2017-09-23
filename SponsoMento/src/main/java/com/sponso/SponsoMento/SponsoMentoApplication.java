package com.sponso.SponsoMento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sponso"})
@EnableJpaRepositories("com.sponso.repo")
@EntityScan("com.sponso.Pojo")
public class SponsoMentoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SponsoMentoApplication.class, args);
	}
}
