package com.project.aoc.controllers;

import com.project.aoc.structures.Node;
import com.project.aoc.structures.Tree;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("8")
public class DayEight {

    private String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_8.txt";

    private int[][] treeArray;

    private boolean[][] treeVisibleArray;

    @PostConstruct // This method will be called when the class is initialized
    public void init() {
        processInputFile();
        setBooleanArray();
        checkAllVisibility();
    }

    public void processInputFile() {
        ArrayList<int[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] row = new int[line.length()];
                for (int j = 0; j < line.length(); j++) {
                    row[j] = Character.getNumericValue(line.charAt(j));
                }
                list.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Converts ArrayList to 2D array
        treeArray = list.toArray(new int[list.size()][]);
    }

    public void setBooleanArray() {
        treeVisibleArray = new boolean[treeArray[1].length][treeArray.length];
    }

    public void checkAllVisibility() {
        int ySize = treeArray.length;
        int xSize = treeArray[1].length;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++){
                checkTreeVisibility(i,j);
            }
        }
    }

    public void checkTreeVisibility(int xPos, int yPos) {
        int ySize = treeArray.length;
        int xSize = treeArray[1].length;
        int treeHeight = treeArray[xPos][yPos];
        boolean leftVisibility = true, rightVisibility = true, topVisibility = true, bottomVisibility = true;

        for (int x = xPos + 1; x < xSize; x += 1) {
            int otherHeight = treeArray[x][yPos];
            if (otherHeight >= treeHeight) {
                rightVisibility = false;
                break;
            }
        }
        for (int x = xPos - 1; x > 0; x -= 1) {
            int otherHeight = treeArray[x][yPos];
            if (otherHeight >= treeHeight) {
                leftVisibility = false;
                break;
            }
        }
        for (int y = xPos + 1; y < ySize; y += 1) {
            int otherHeight = treeArray[xPos][y];
            if (otherHeight >= treeHeight) {
                topVisibility = false;
                break;
            }
        }
        for (int y = yPos - 1; y > 0; y -= 1) {
            int otherHeight = treeArray[xPos][y];
            if (otherHeight >= treeHeight) {
                bottomVisibility = false;
                break;
            }
        }

        if (!leftVisibility && !rightVisibility && !topVisibility && !bottomVisibility)
            treeVisibleArray[xPos][yPos] = false;
        else
            treeVisibleArray[xPos][yPos] = true;
    }

    public int getSum(boolean[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j]) { // If the value is true
                    sum += 1; // Add 1 to the sum
                }
            }
        }
        return sum;
    }

    @GetMapping("1")
    public int getAnswerOne() {
        return getSum(treeVisibleArray);
    }

    @GetMapping("2")
    public int getAnswerTwo() {
        return 0;
    }

    private long countLines(String filePath) {
        long lines = 0;
        try (Stream<String> lineStream = new BufferedReader(new FileReader(filePath)).lines()) {
            lines = lineStream.count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}