package com.julianzipper.cicddemo.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.julianzipper.cicddemo.entity.Recipe;
import com.julianzipper.cicddemo.exception.NotFoundException;
import com.julianzipper.cicddemo.repository.RecipeRepository;

import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;
    
    public RecipeService(RecipeRepository recipeRepository, ObjectMapper objectMapper) {
        this.recipeRepository = recipeRepository;
        this.objectMapper = objectMapper;
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public Recipe getById(Long id) {
        return findRecipe(id);
    }

    public Recipe save(Recipe newRecipe) {
        return recipeRepository.save(newRecipe);
    }

    public Recipe patch(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Recipe foundRecipe = findRecipe(id);
        Recipe updatedRecipe = applyPatchToRecipe(patch, foundRecipe);
        return save(updatedRecipe);
    }

    public void delete(Long id) {
        recipeRepository.delete(findRecipe(id));
    }

    // ------- helper methods -------- //

    private Recipe findRecipe(Long recipeId) {
        Recipe foundRecipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new NotFoundException(recipeId, "Recipe"));
        return foundRecipe;
    }

    private Recipe applyPatchToRecipe(JsonPatch patch, Recipe targetRecipe) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetRecipe, JsonNode.class));
        return objectMapper.treeToValue(patched, Recipe.class);
    }
}