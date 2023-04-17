package com.project.aoc.controllers;
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
@RequestMapping("3")
public class DayThree {

    @GetMapping("1")
    public int getAnswerThree(){
        String inputFilePath = "C:\\Users\\elvis\\projects\\aoc_new\\data\\scraped_data_2.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()){
                    continue;// go to next line
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
