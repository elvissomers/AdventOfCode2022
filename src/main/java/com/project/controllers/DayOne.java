package com.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("1")
public class DayOne {
    @GetMapping("1")
    public int getAnswerOne(){
        String inputFilePath = "C:\\Users\\elvis\\projects\\aoc_new\\data\\scraped_data_1.txt";
        ArrayList<Integer> elfCalories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int elfCount = 0;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Integer currentCalories = elfCalories.get(elfCount);
                    elfCalories.set(elfCount, currentCalories + Integer.parseInt(line.trim()));
                } else {
                    elfCount += 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
