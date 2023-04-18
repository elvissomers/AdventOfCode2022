package com.project.aoc.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(DayFive.class);
    /**
     * Helper functions from here -
     */
    public List<Deque<Character>> getStartStacks(List<String> startLines) {
        List<Deque<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            stacks.add(new ArrayDeque<>());
        }

        Collections.reverse(startLines);
        for (String line : startLines) {
            for (int i = 0; i < 9; i++ ){
                int letterPos = 4*i + 1;
                char c = line.charAt(letterPos);
                if (Character.isLetter(c)) {
                    stacks.get(i).push(line.charAt(letterPos));
                }
            }
        }

        return stacks;
    }

    @GetMapping("1")
    public String getAnswerOne() {
        String inputFilePath = "C:\\Users\\elvis\\projects\\aoc_new\\data\\scraped_data_4.txt";
        List<String> startLines = new ArrayList<>();
        List<Deque<Character>> stacks = new ArrayList<>();
        logger.info("Tjhis working?");
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
                    String[] words = line.split(" ");
                    int amount = Integer.parseInt(words[1]);
                    int fromStack = Integer.parseInt(words[3]) - 1;
                    int toStack = Integer.parseInt(words[5]) - 1;

                    for (int i = 0; i < amount; i++){
                        char moveCrate = stacks.get(fromStack).pop();
                        stacks.get(toStack).push(moveCrate);
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (Deque<Character> stack : stacks){
            char topLetter = stack.pop();
            logger.info("Hellooohoo?");
            logger.info(String.valueOf(topLetter));
            sb.append(topLetter);
        }
        return sb.toString();
    }
}