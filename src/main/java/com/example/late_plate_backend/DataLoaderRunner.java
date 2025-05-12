package com.example.late_plate_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class DataLoaderRunner implements CommandLineRunner {
    private final CSVLoaderService csvLoader;

    public DataLoaderRunner(CSVLoaderService csvLoader) {
        this.csvLoader = csvLoader;
    }

    @Override
    public void run(String... args) {
        System.out.println("📥 CSV Loader runner started...");
        //"C:\Users\andre\Desktop\studies\GP\recipes_data.csv\recipes_data.csv"
        //String path= "C:\\Users\\andre\\Desktop\\studies\\GP\\recipes_data.csv\\recipes_data.csv";
        //csvLoader.loadCSV("C:/Users/andre/Desktop/studies/GP/recipe data/recipes_data.csv");
    }
}
