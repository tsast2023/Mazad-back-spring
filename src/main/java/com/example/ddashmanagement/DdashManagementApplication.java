package com.example.ddashmanagement;

import com.example.ddashmanagement.Ennum.RoleUser;
import com.example.ddashmanagement.Ennum.StatusProduct;
import com.example.ddashmanagement.Entites.Administrateur;
import com.example.ddashmanagement.Entites.Category;
import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Repository.AdminRepository;
import com.example.ddashmanagement.Repository.CategoryRepository;
import com.example.ddashmanagement.Repository.ProductRepository;
import com.example.ddashmanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
@EnableMongoAuditing
public class DdashManagementApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;

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


	@Override
	public void run(String... args) throws Exception {
		Administrateur adminAccount = adminRepository.findByRole(RoleUser.SuperAdmin);
		if(null == adminAccount){
			Administrateur admin = new Administrateur();
			admin.setEmail("admin@gmail.com");
			admin.setPassword(new BCryptPasswordEncoder().encode("123456789"));
			admin.setPrenom("admin");
			admin.setRole(RoleUser.SuperAdmin);
			userRepository.save(admin);

		}


	}
}
