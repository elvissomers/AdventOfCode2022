package com.project.aoc.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("2")
public class DayTwo {

    private final String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_2.txt";

    @GetMapping("1")
    public int getAnswerOne(){
        int score = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            getScoreFromFileReader(score, reader, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return score;
    }

    @GetMapping("2")
    public int getAnswerTwo(){
        int score = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            getScoreFromFileReader(score, reader, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return score;
    }

    private void getScoreFromFileReader(int score, BufferedReader reader, boolean strategyOneUsed) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()){
                continue;// go to next line
            }
            char firstChar = line.charAt(0);
            char secondChar = line.charAt(2);

            score = (strategyOneUsed) ? increaseScoreForStrategyOne(score, firstChar, secondChar) :
                increaseScoreForStrategyTwo(score, firstChar, secondChar);
        }
    }

    private int increaseScoreForStrategyOne(int score, char firstChar, char secondChar) {
        if (secondChar == 'X'){
            score += 1;
            if (firstChar == 'A'){
                score += 3;
            } else if (firstChar == 'C'){
                score += 6;
            }
        } else if (secondChar == 'Y'){
            score += 2;
            if (firstChar == 'B'){
                score += 3;
            } else if (firstChar == 'A'){
                score += 6;
            }
        } else if (secondChar == 'Z'){
            score += 3;
            if (firstChar == 'C'){
                score += 3;
            } else if (firstChar == 'B'){
                score += 6;
            }
        }
        return score;
    }

    private int increaseScoreForStrategyTwo(int score, char firstChar, char secondChar) {
        if (secondChar == 'X'){
            if (firstChar == 'A'){
                score += 3;
            } else if (firstChar == 'B'){
                score += 1;
            } else if (firstChar == 'C'){
                score += 2;
            }
        } else if (secondChar == 'Y'){
            score += 3;
            if (firstChar == 'A'){
                score += 1;
            } else if (firstChar == 'B'){
                score += 2;
            } else if (firstChar == 'C'){
                score += 3;
            }
        } else if (secondChar == 'Z'){
            score += 6;
            if (firstChar == 'A'){
                score += 2;
            } else if (firstChar == 'B'){
                score += 3;
            } else if (firstChar == 'C'){
                score += 1;
            }
        }
        return score;
    }
}
