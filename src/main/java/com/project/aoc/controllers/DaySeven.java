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
import java.util.stream.Stream;

@RestController
@RequestMapping("7")
public class DaySeven {

    @GetMapping("1")
    public int getAnswerOne() {
        // TODO: im assuming there are no doubled file names!
        // TODO: this is not the case!! File names are doubles!
        String inputFilePath = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_7.txt";
        int ans = 0;
        Tree fileSystem = new Tree();
        fileSystem.put("/", new Node(0, null)); // Setting base node
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            String currentNodeTag = null;
            String currentParentTag = null;
            boolean readingLsOutput = false;

            long totalLines = countLines(inputFilePath); // get total lines for progress tracking
            long currentLine = 0;

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
                            //TODO : take into account the possibility of multiple "cd /" commands (not the case in input)
                        } else {
                            // TODO: node is already in tree!
                            currentParentTag = currentNodeTag;
                            currentNodeTag += "/" + words[2];
                        }

                    } else if (words[1].equals("ls")) {

//                        readListOutput(fileSystem, reader, currentNodeTag);
                        //TODO
                        readingLsOutput = true;

                    }
                    continue;
                }

                if (readingLsOutput) {
                    int dataSize = (words[0].equals("dir")) ? 0 : Integer.parseInt(words[0]);
                    Node newNode = new Node(dataSize,currentNodeTag);
                    // TODO: also add data size to all parent node, and to their parent etc
                    // TODO: change files tags into entire name, starting from /
                    String childNodeTag = currentNodeTag + "/" + words[1];
                    String tempParentTag = currentNodeTag;

                    while (tempParentTag != null) {
                        String currentTag = tempParentTag;
                        Node currentNode = fileSystem.get(currentTag);
                        tempParentTag = currentNode.getParent();
//                        System.out.println(currentTag);
//                        System.out.println(currentNode.getData());
//                        System.out.println(currentNode.getData() + dataSize);
                        fileSystem.put(currentTag, new Node(currentNode.getData() + dataSize, tempParentTag,
                                currentNode.getChildren()));
                    }
//                    System.out.println(childNodeTag);
//                    System.out.println(dataSize);
                    fileSystem.put(childNodeTag, newNode);
                    fileSystem.get(currentNodeTag).addChild(words[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        printKids(fileSystem);
        ans = getAns(fileSystem);
        int ansTwo = getAnsTwo(fileSystem);
        return ans;
    }

    /**
     * Helper methods from here
     */
    public void printKids(Tree fileSystem) {
        System.out.println("Debugging.... printie..");
        for (Node node : fileSystem.values()){
            System.out.println(node.getData());
            System.out.println(node.getChildren());
        }
    }
    public int getAns(Tree fileSystem){
        int sumOfValuesLessThan100000 = fileSystem.values().stream()
                .filter(node -> node.getData() < 100000)
                .filter(node -> !node.getChildren().isEmpty())
                .mapToInt(Node::getData)
                .sum();
        // Return the sum of elements
        return sumOfValuesLessThan100000;
    }

    public int getAnsTwo(Tree fileSystem) {

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
        bar.append("]   " + 2.5*percent + "%     (" + completed + "/" + total + ")");
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