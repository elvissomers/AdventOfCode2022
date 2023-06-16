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
@RequestMapping("7")
public class DaySeven {
    private String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_7.txt";
    private Tree fileSystem = new Tree();

    @PostConstruct // This method will be called when the class is initialized
    public void init() {
        fileSystem.put("/", new Node(0, null)); // Setting base node
        processInputFile();
    }

    @GetMapping("1")
    public int getAnswerOne() {
        return getAns(fileSystem);
    }

    @GetMapping("2")
    public int getAnswerTwo() {
        return getAnsTwo(fileSystem);
    }

    /**
     * Helper methods from here
     */
    public int getAns(Tree fileSystem) {
        return fileSystem.values().stream()
                .filter(node -> node.getData() < 100000)
                .filter(node -> !node.getChildren().isEmpty())
                .mapToInt(Node::getData)
                .sum();
    }

    public int getAnsTwo(Tree fileSystem) {
        int systemSize = 70000000;
        int requiredSpace = 30000000;
        int availableSpace = systemSize - fileSystem.get("/").getData();

        return fileSystem.values().stream()
                .filter(node -> node.getData() + availableSpace >= requiredSpace)
                .mapToInt(Node::getData)
                .min()
                .getAsInt();
    }

    private void processInputFile() {
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

                if (words[0].equals("$")) { // This is a command
                    if (words[1].equals("cd")) {
                        readingLsOutput = false;
                        if (words[2].equals("..")) {
                            currentNodeTag = currentParentTag;
                            assert currentNodeTag != null;
                            currentParentTag = fileSystem.get(currentNodeTag).getParent();
                        } else if (words[2].equals("/")) {
                            currentNodeTag = words[2];
                        } else {
                            currentParentTag = currentNodeTag;
                            currentNodeTag += "/" + words[2];
                        }

                    } else if (words[1].equals("ls")) {
                        readingLsOutput = true;

                    }
                    continue;
                }

                if (readingLsOutput) {
                    int dataSize = (words[0].equals("dir")) ? 0 : Integer.parseInt(words[0]);
                    Node newNode = new Node(dataSize, currentNodeTag);
                    String childNodeTag = currentNodeTag + "/" + words[1];
                    String tempParentTag = currentNodeTag;

                    while (tempParentTag != null) {
                        String currentTag = tempParentTag;
                        Node currentNode = fileSystem.get(currentTag);
                        tempParentTag = currentNode.getParent();
                        fileSystem.put(currentTag, new Node(currentNode.getData() + dataSize, tempParentTag,
                                currentNode.getChildren()));
                    }
                    fileSystem.put(childNodeTag, newNode);
                    fileSystem.get(currentNodeTag).addChild(words[1]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printProgressBar(long completed, long total) {
        int percent = (int) ((completed * 40) / total);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 40; i++) {
            if (i < percent) {
                bar.append('#');
            } else if (i == percent) {
                bar.append('>');
            } else {
                bar.append(' ');
            }
        }
        bar.append("]   " + 2.5 * percent + "%     (" + completed + "/" + total + ")");
        System.out.print('\r' + bar.toString());
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