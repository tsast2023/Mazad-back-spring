package com.example.ddashmanagement;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.io.IOException;

@SpringBootApplication
@EnableMongoAuditing
public class DdashManagementApplication{



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

	@Bean
	FirebaseMessaging firebaseMessaging() {
		try {
			GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
					new ClassPathResource("mazedpushnotification-firebase-adminsdk-3ce8d-d8ca43e04f.json").getInputStream());
			FirebaseOptions firebaseOptions = FirebaseOptions.builder()
					.setCredentials(googleCredentials).build();
			if (FirebaseApp.getApps().isEmpty()) { // Vérifiez si l'app a déjà été initialisée
				FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "My-App");
				return FirebaseMessaging.getInstance(app);
			} else {
				return FirebaseMessaging.getInstance(FirebaseApp.getInstance("My-App"));
			}
		} catch (IOException e) {
			throw new RuntimeException("Échec de l'initialisation de Firebase Messaging", e);
		}

}}

