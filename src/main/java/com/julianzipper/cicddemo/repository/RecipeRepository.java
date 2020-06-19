package com.julianzipper.cicddemo.repository;

import java.util.List;

import com.julianzipper.cicddemo.entity.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    List<Recipe> findByName(String name);

    // TODO: implement a method for finding recipes by ingredient(s)
    // TODO: implement a method for finding recipes by tag(s)
}