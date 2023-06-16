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
            boolean readingLsOutput = false;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] words = line.split(" ");
                if (readingLsOutput) {
                    int dataSize = (words[0].equals("dir")) ? 0 : Integer.parseInt(words[0]);
                    Node newNode = new Node(dataSize,currentNodeTag);
                    fileSystem.put(words[1], newNode);
                    fileSystem.get(currentNodeTag).addChild(words[1]);
                }

                if (words[0].equals("$")) { // This is a command
                    if (words[1].equals("cd")) {
                        readingLsOutput = false;
                        if (words[2].equals("..")) {
                            currentNodeTag = currentParentTag;
                            assert currentNodeTag != null;
                            currentParentTag = fileSystem.get(currentNodeTag).getParent();
                        } else if (words[2].equals("/")) {
                            continue;
                            //TODO : take into account the possibility of multiple "cd /" commands (not the case in input)
                        } else {
                            // TODO: node is already in tree!
                            currentParentTag = currentNodeTag;
                            currentNodeTag = words[3];
                        }

                    } else if (words[1].equals("ls")) {

//                        readListOutput(fileSystem, reader, currentNodeTag);
                        //TODO
                        readingLsOutput = true;

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
        String line;
        try (reader) {
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;// go to next line
            }
            String[] words = line.split(" ");
            if (words[0].equals("$"))
                return;

            int dataSize = (words[0].equals("dir")) ? 0 : Integer.parseInt(words[0]);
            Node newNode = new Node(dataSize,nodeName);
            tree.put(words[1], newNode);
            node.addChild(words[1]);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}