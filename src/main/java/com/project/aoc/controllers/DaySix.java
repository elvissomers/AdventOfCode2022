package com.project.aoc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("6")
public class DaySix {

    @GetMapping("1")
    public int getAnswerOne() {
        String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_6.txt";
        int noRepeat = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;// go to next line
                }
                else {
                    char[] letters = line.toCharArray();
                    charReader:
                    for (int i = 0; i < line.length()-3; i++){
                        char[] fourLetterGroup = Arrays.copyOfRange(letters, i, i+4);
                        for (int k = 0; k < 4; k++){
                            for (int j = 0; j < 4; j++){
                                if (k !=j && fourLetterGroup[k] == fourLetterGroup[j])
                                    continue charReader;
                            }
                        }
                        noRepeat = i+4;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return noRepeat;
    }

    @GetMapping("2")
    public int getAnswerTwo() {
        String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_6.txt";
        int noRepeat = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;// go to next line
                }
                else {
                    char[] letters = line.toCharArray();
                    charReader:
                    for (int i = 0; i < line.length()-13; i++){
                        char[] fourLetterGroup = Arrays.copyOfRange(letters, i, i+14);
                        for (int k = 0; k < 14; k++){
                            for (int j = 0; j < 14; j++){
                                if (k !=j && fourLetterGroup[k] == fourLetterGroup[j])
                                    continue charReader;
                            }
                        }
                        noRepeat = i+14;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return noRepeat;
    }
}
