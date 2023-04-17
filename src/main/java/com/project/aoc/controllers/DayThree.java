package com.project.aoc.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("3")
public class DayThree {

    @GetMapping("1")
    public int getAnswerThree(){
        String inputFilePath = "C:\\Users\\elvis\\projects\\aoc_new\\data\\scraped_data_2.txt";
        List<Character> doubledItems = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()){
                    continue;// go to next line
                }
                String str = "Hello World";
                int mid = line.length() /2;

                String firstHalf = line.substring(0, mid); // Extracts the first half of the string
                String secondHalf = line.substring(mid); // Extracts the second half of the string

                for (int i=0; i < secondHalf.length(); i++){
                    char c = secondHalf.charAt(i);
                    if (firstHalf.contains(Character.toString(c))){
                        doubledItems.add(c);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
