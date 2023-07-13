package com.project.aoc.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

@RestController
@RequestMapping("1")
public class DayOne {

    private final String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_1.txt";


    @GetMapping("1")
    public Integer getAnswerOne(){
        ArrayList<Integer> elfCalories = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            getElfCaloriesFromFileReader(elfCalories, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return elfCalories.stream().max(Integer::compareTo).orElse(0);
    }

    private void getElfCaloriesFromFileReader(ArrayList<Integer> elfCalories,
                                                            BufferedReader reader) throws IOException {
        String line;
        int elfCount = 0;
        elfCalories.add(0);

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                Integer currentCalories = elfCalories.get(elfCount);
                elfCalories.set(elfCount, currentCalories + Integer.parseInt(line.trim()));
            } else {
                elfCount += 1;
                elfCalories.add(0);
            }
        }
    }

    @GetMapping("2")
    public ResponseEntity<Integer> getAnswerTwo(){
        ArrayList<Integer> elfCalories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int elfCount = 0;
            elfCalories.add(0);
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Integer currentCalories = elfCalories.get(elfCount);
                    elfCalories.set(elfCount, currentCalories + Integer.parseInt(line.trim()));
                } else {
                    elfCount += 1;
                    elfCalories.add(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int answer = elfCalories.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(answer);

    }
}
