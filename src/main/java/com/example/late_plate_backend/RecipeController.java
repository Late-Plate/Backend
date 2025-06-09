package com.example.late_plate_backend;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeService recipeService;
    public RecipeController(RecipeService recipeService){
        this.recipeService=recipeService;
    }
    @GetMapping
    public Page<Recipe> getRecipes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return recipeService.getRecipes(page, size);
    }
    @GetMapping("/by-name")
    public Page<Recipe> getRecipesByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return recipeService.getByName(page, size,name);
    }
    @GetMapping("/by-ingredient")
    public Page<Recipe> getIngredients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String ingredient
    ){
        return recipeService.searchRecipesbyIngredient(page, size, ingredient);
    }
    @GetMapping("/general-search")
    public Page<Recipe> Search(
            @RequestParam String term,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return recipeService.searchRecipes(page, size,term);
    }

}

