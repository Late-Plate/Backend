package com.example.late_plate_backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Page<Recipe> findAll(Pageable pageable);

    @Query("SELECT r FROM recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :searchedName, '%'))"
    )Page<Recipe>findNameContaing(@Param("searchedName") String name,Pageable pageable);

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.ingredients) LIKE LOWER(CONCAT('%', :ingredient, '%'))")
    List<Recipe> findByIngredientContaining(@Param("ingredient") String ingredient);
    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
                  "OR LOWER(r.ingredients) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Recipe> searchByNameOrIngredients(@Param("searchTerm") String searchTerm,Pageable pageable);
}
