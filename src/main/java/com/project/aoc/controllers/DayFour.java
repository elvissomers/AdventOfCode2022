package com.project.aoc.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("4")
public class DayFour {

    @GetMapping("1")
    public int getAnswerOne() {
        String inputFilePath = "C:\\Users\\elvis\\projects\\aoc_new\\data\\scraped_data_4.txt";
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            reader:
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;// go to next line
                }
                String[] numberStrings = line.split("[-,]");
                int[] numbers = Arrays.stream(numberStrings).mapToInt(Integer::parseInt).toArray();

                if (numbers[3] >= numbers[1] && numbers[2] <= numbers[0]) {
                    count += 1;
                }
                if (numbers[1] >= numbers[3] && numbers[0] <= numbers[2]) {
                    count += 1;
                }
                if (numbers[1] == numbers[3] && numbers[2] == numbers[0]) {
                    count -= 1;
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return count;
    }

    @GetMapping("2")
    public int getAnswerTwo() {
        String inputFilePath = "C:\\Users\\elvis\\projects\\aoc_new\\data\\scraped_data_4.txt";
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            reader:
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;// go to next line
                }
                String[] numberStrings = line.split("[-,]");
                int[] numbers = Arrays.stream(numberStrings).mapToInt(Integer::parseInt).toArray();

                if ((numbers[3] >= numbers[1] && numbers[2] <= numbers[1]) || numbers[1] >= numbers[3] && numbers[0] <= numbers[3]) {
                    count += 1;
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return count;
    }

}