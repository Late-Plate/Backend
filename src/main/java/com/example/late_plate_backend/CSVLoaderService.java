package com.example.late_plate_backend;
import com.example.late_plate_backend.Recipe;
import com.example.late_plate_backend.RecipeRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CSVLoaderService {
    private final RecipeRepository recipeRepository;
    @Autowired
    public CSVLoaderService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void loadCSV(String filePath) {
        System.out.println("👉 loadCSV() was called with path: " + filePath);

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("❌ File does NOT exist at: " + filePath);
            return;
        } else {
            System.out.println("✅ File exists");
        }

        try (
                FileReader fileReader = new FileReader(file);
                CSVReader csvReader = new CSVReader(fileReader)
        ) {
            System.out.println("📥 in try");

            String[] row;
            int rowCount = 0;
            int batchSize = 1000;
            List<Recipe> batch = new ArrayList<>();

            // Skip header
            csvReader.readNext();

            while ((row = csvReader.readNext()) != null) {
                rowCount++;

                if (row.length < 3) {
                    System.out.println("⚠️ Skipping malformed row at line " + rowCount);
                    continue;
                }

                String name = row[0].trim();
                String ingredients = row[1].trim();
                String instructions = row[2].trim();

                int maxNameLength = 500;
                int maxIngredientsLength = 6500;
                int maxInstructionsLength = 6500;

                if (name.length() > maxNameLength || ingredients.length() > maxIngredientsLength || instructions.length() > maxInstructionsLength) {
                    System.out.println("⚠️ Skipping long row at line " + rowCount + " — name/ingredients/instructions too long.");
                    continue;
                }

                Recipe recipe = new Recipe(name, ingredients, instructions);
                batch.add(recipe);

                if (batch.size() >= batchSize) {
                    recipeRepository.saveAll(batch);
                    batch.clear(); // reset the batch
                    System.out.println("✅ Saved batch at row: " + rowCount);
                }
            }

            // Save any remaining recipes
            if (!batch.isEmpty()) {
                recipeRepository.saveAll(batch);
                System.out.println("✅ Saved final batch of size: " + batch.size());
            }

            System.out.println("🎉 Done. Total rows processed: " + rowCount);

        } catch (Exception e) {
            System.out.println("❗ Caught exception:");
            e.printStackTrace();
        }
    }
}
