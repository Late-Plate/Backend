package com.example.late_plate_backend;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public Page<Recipe> getRecipes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return recipeRepository.findAll(pageable);
    }
    public Page<Recipe> getByName(int page, int size, String name){
        Pageable pageable = PageRequest.of(page,size,Sort.by("name").ascending());
        if (name == null || name.trim().isEmpty()) {
            // if no search term provided, return all recipes
            return recipeRepository.findAll(pageable);
        }
        return recipeRepository.findNameContaining(name,pageable);
    }


    public Page<Recipe> searchRecipesbyIngredient(int page,int size,String ingredient) {
        Pageable pageable = PageRequest.of(page,size,Sort.by("name").ascending());
        if (ingredient == null || ingredient.trim().isEmpty()) {
            // if no search term provided, return all recipes
            return recipeRepository.findAll(pageable);
        }
        return recipeRepository.findByIngredientContaining(ingredient,pageable);
    }
    public Page<Recipe> searchRecipes(int page,int size,String term){
        Pageable pageable = PageRequest.of(page,size,Sort.by("name").ascending());
        if (term == null || term.trim().isEmpty()) {
            // if no search term provided, return all recipes
            return recipeRepository.findAll(pageable);
        }
        return recipeRepository.searchByNameOrIngredients(term,pageable);
    }
}

