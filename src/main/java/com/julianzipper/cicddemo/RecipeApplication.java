package com.julianzipper.cicddemo;

import com.julianzipper.cicddemo.entity.Recipe;
import com.julianzipper.cicddemo.repository.RecipeRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeApplication.class, args);
	}

	@Bean
	public CommandLineRunner preloadData(RecipeRepository recipeRepository) {
		return (args) -> {
			recipeRepository.save(new Recipe("Pasta Bolognese", "Tomato-y goodness with noodles", "Spaghetti, Tomato Sauce, Basil, Onion, Garlic", "Cook your pasta, chop the veggies and fry. add sauce and herbs and combine."));
			recipeRepository.save(new Recipe("Pasta Alfredo", "Deliciously creamy", "Tagliatelle, Heavy Cream, Parmezan Cheese, garlic, italian herbs", "Cook your pasta, drain and toss in a frying pan with garlic and italian herbs. Finish with heavy cream and grated cheese. Serve hot!"));
		};
	}

}
