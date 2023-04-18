package com.project.aoc.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("5")
public class DayFive {


    /**
     * Helper functions from here -
     */
    public List<Deque<Character>> getStartStacks(List<String> startLines) {
        List<Deque<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            stacks.add(new ArrayDeque<>());
        }
        for (String line : startLines) {
            for (int i = 0; i < 9; i++ ){
                int letterPos = 4*i + 1;
                stacks.get(i).offer(line.charAt(letterPos));
            }
        }

        return stacks;
    }

    @GetMapping("1")
    public String getAnswerOne() {
        String inputFilePath = "C:\\Users\\elvis\\projects\\aoc_new\\data\\scraped_data_4.txt";
        List<String> startLines = new ArrayList<>();
        List<Deque<Character>> stacks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean readStart = false;
            reader:
            while ((line = reader.readLine()) != null) {
                if (line.replaceAll("\\s+", "").equals("123456789")) {
                    // Call the getStartStacks() helper function with the list of startLines
                    stacks = getStartStacks(startLines);
                    readStart = true;
                }
                if (!readStart){
                    startLines.add(line);
                }
                if (readStart && !line.trim().isEmpty()){

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "ABA";
    }
}