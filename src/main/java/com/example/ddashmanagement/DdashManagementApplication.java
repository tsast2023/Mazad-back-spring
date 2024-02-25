package com.example.ddashmanagement;

import com.example.ddashmanagement.Ennum.StatusProduct;
import com.example.ddashmanagement.Entites.Category;
import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Repository.CategoryRepository;
import com.example.ddashmanagement.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
@EnableMongoAuditing
public class DdashManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdashManagementApplication.class, args);
	}

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

}
