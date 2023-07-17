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

    private final String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_4.txt";

    @GetMapping("1")
    public int getAnswerOne() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                int[] numbers = getNumbersFromLine(line);
                if (intervalIsFullyContainedWithinInterval(numbers[0], numbers[1], numbers[2], numbers[3])
                        || intervalIsFullyContainedWithinInterval(numbers[2], numbers[3], numbers[0], numbers[1]))
                    count += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private int[] getNumbersFromLine(String line) {
        String[] numberStrings = line.split("[-,]");
        return Arrays.stream(numberStrings)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private boolean intervalIsFullyContainedWithinInterval(int minOfIntervalOne, int maxOfIntervalOne,
                                                           int minOfIntervalTwo, int maxOfIntervalTwo) {
        return (maxOfIntervalTwo >= maxOfIntervalOne && minOfIntervalTwo <= minOfIntervalOne);
    }

    @GetMapping("2")
    public int getAnswerTwo() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
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