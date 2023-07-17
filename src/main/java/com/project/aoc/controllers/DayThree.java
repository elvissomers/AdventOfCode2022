package com.project.aoc.controllers;
import com.project.aoc.exception.ElementNotFoundException;
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

    private final String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_3.txt";

    @GetMapping("1")
    public int getAnswerOne(){
        List<Character> doubledItems = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            getDoubledItemsFromFileReader(doubledItems, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getSumOfCharacterValues(doubledItems);
    }

    private void getDoubledItemsFromFileReader(List<Character> doubledItems, BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()){
                continue;
            }
            doubledItems.add(findCommonCharacterInFirstAndSecondHalfOfString(line));
        }
    }

    private char findCommonCharacterInFirstAndSecondHalfOfString(String string) {
        int mid = string.length() /2;

        String firstHalf = string.substring(0, mid);
        String secondHalf = string.substring(mid);

        try {
            return findCommonCharacterInStrings(firstHalf, secondHalf);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
            return 'E';
        }
    }

    private char findCommonCharacterInStrings(String string1, String string2) throws ElementNotFoundException {
        for (int i=0; i < string1.length(); i++){
            char c = string1.charAt(i);
            if (string2.contains(Character.toString(c))){
                return c;
            }
        }
        throw new ElementNotFoundException("No common character in these strings!");
    }

    private int getSumOfCharacterValues(List<Character> doubledItems) {
        return doubledItems.stream()
                // This map converts the characters to integers using the following rule: a-z = 1-26; A-Z = 27-52
                .map(c -> (c >= 'a' && c <= 'z') ? (c - 'a' + 1) : (c >= 'A' && c <= 'Z') ? (c - 'A' + 27) : 0)
                .mapToInt(Integer::intValue).sum();
    }

    @GetMapping("2")
    public int getAnswerTwo() {
        List<Character> doubledItems = new ArrayList<>();
        List<String> group = new ArrayList<>();
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            reader: while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;// go to next line
                }

                // I want to collect the lines in groups of 3
                group.add(line);
                count++;
                if (count == 3) {
                    for (int i=0; i < group.get(0).length(); i++){
                        char c = group.get(0).charAt(i);
                        if (group.get(1).contains(Character.toString(c))){
                            if (group.get(2).contains(Character.toString(c))) {
                                doubledItems.add(c);
                                // Reset the counter and group
                                count = 0;
                                group.clear();
                                continue reader;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> convertedNumbers = doubledItems.stream()
                .map(c -> (c >= 'a' && c <= 'z') ? (c - 'a' + 1) : (c >= 'A' && c <= 'Z') ? (c - 'A' + 27) : 0)
                .collect(Collectors.toList());

        int sum = convertedNumbers.stream().mapToInt(Integer::intValue).sum();

        return sum;
    }
}
