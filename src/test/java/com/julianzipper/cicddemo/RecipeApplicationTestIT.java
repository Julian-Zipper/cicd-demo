package com.julianzipper.cicddemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RecipeApplicationTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllRecipes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipes").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	public void getSingleRecipe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipes/1").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();

	}
	@Test
	public void getNonExistentRecipe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/recipes/15").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andReturn();
	}

	@Test
	public void addNewRecipe() throws Exception {
		String newRecipeJSON = String.format(
			"{\"name\":\"%s\", \"description\":\"%s\", \"ingredients\":\"%s\", \"directions\":\"%s\"}",
			"Pepperoni Pineapple Pizza",
			"Your passport will be denied in Italy, but it will be worth it",
			"Pizza base, tomato sauce, grated cheese, pineapple, pepperoni",
			"Circle a large serving spoon with tomato sauce over the pizza base. Add cheese and toppings. Toss in the oven for a bit and enjoy your sacrilege."
			);
		mockMvc.perform(MockMvcRequestBuilders.post("/recipes").contentType(MediaType.APPLICATION_JSON).content(newRecipeJSON).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andReturn();
	}

	@Test
	public void updateRecipeName() throws Exception {
		String jsonPatchString = "[{\"op\":\"replace\", \"path\":\"/name\", \"value\":\"Pasta Mozzarella-de-luxe\"}]";
		mockMvc.perform(MockMvcRequestBuilders.patch("/recipes/1").contentType("application/json-patch+json").content(jsonPatchString).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	public void removeRecipe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/recipes/2").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	}

}
