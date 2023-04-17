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
import java.util.stream.Collectors;

@RestController
@RequestMapping("3")
public class DayThree {

    @GetMapping("1")
    public int getAnswerThree(){
        String inputFilePath = "C:\\Users\\elvis\\projects\\aoc_new\\data\\scraped_data_3.txt";
        List<Character> doubledItems = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            reader: while ((line = reader.readLine()) != null) {
                if (line.isEmpty()){
                    continue;// go to next line
                }
                int mid = line.length() /2;

                String firstHalf = line.substring(0, mid); // Extracts the first half of the string
                String secondHalf = line.substring(mid); // Extracts the second half of the string

                for (int i=0; i < secondHalf.length(); i++){
                    char c = secondHalf.charAt(i);
                    if (firstHalf.contains(Character.toString(c))){
                        doubledItems.add(c);
                        continue reader;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        int uppercaseCount = (int) doubledItems.stream().filter(Character::isUpperCase).count();

        List<Integer> convertedNumbers = doubledItems.stream()
                .map(c -> (c >= 'a' && c <= 'z') ? (c - 'a' + 1) : (c >= 'A' && c <= 'Z') ? (c - 'A' + 27) : 0)
                .collect(Collectors.toList());

        int sum = convertedNumbers.stream().mapToInt(Integer::intValue).sum();

        return sum;
    }
}
