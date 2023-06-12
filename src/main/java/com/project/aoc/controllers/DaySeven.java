package com.project.aoc.controllers;

import com.project.aoc.structures.Node;
import com.project.aoc.structures.Tree;
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
@RequestMapping("7")
public class DaySeven {

    @GetMapping("1")
    public int getAnswerOne() {
        String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_7.txt";
        int ans = 0;
        Tree fileSystem = new Tree();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            String currentNodeTag = null;
            String currentParentTag = null;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;// go to next line
                }
                String[] words = line.split(" ");
                if (words[0].equals("$")) { // This is a command
                    if (words[1].equals("cd")) {
                        if (!words[2].equals("..")){
                            Node newNode = new Node(0, currentParentTag);
                            currentNodeTag = words[2];
                            fileSystem.put(words[3], newNode);
                        } else {
                            currentNodeTag = currentParentTag;
                            assert currentNodeTag != null;
                            currentParentTag = fileSystem.get(currentNodeTag).getParent();
                        }
                    } else if (words[1].equals("ls")) {
                        readListOutput(fileSystem, reader, currentNodeTag);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ans;
    }

    /**
     * Helper methods from here
     */
    public void readListOutput(Tree tree, BufferedReader reader, String nodeName){
        Node node = tree.get(nodeName);
    }
}