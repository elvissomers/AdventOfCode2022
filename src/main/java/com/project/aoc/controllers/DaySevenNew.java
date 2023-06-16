//package com.project.aoc.controllers;
//
//import com.project.aoc.structures.Node;
//import com.project.aoc.structures.Tree;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//@RestController
//@RequestMapping("7new")
//public class DaySevenNew {
//    private static final String INPUT_FILE_PATH = "C:\\Users\\eso13215\\IdeaProjects\\aoc_2022\\data\\scraped_data_7.txt";
//
//    @GetMapping("1")
//    public int getAnswerOne() {
//        int ans = 0;
//        Tree fileSystem = new Tree();
//        processInputFile(fileSystem);
//        return ans;
//    }
//
//    private void processInputFile(Tree fileSystem) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_PATH))) {
//            String currentNodeTag = null;
//            String currentParentTag = null;
//
//            for (String line; (line = reader.readLine()) != null;) {
//                if (line.isEmpty()) {
//                    continue;
//                }
//
//                processLine(fileSystem, line, currentNodeTag, currentParentTag);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void processLine(Tree fileSystem, String line, String currentNodeTag, String currentParentTag) {
//        String[] words = line.split(" ");
//
//        if (isCommand(words)) {
//            handleCommand(fileSystem, words, currentNodeTag, currentParentTag);
//        }
//    }
//
//    private boolean isCommand(String[] words) {
//        return words[0].equals("$");
//    }
//
//    private void handleCommand(Tree fileSystem, String[] words, String currentNodeTag, String currentParentTag) {
//        if (words[1].equals("cd")) {
//            handleCDCommand(fileSystem, words, currentNodeTag, currentParentTag);
//        } else if (words[1].equals("ls")) {
//            readListOutput(fileSystem, currentNodeTag);
//        }
//    }
//
//    private void handleCDCommand(Tree fileSystem, String[] words, String currentNodeTag, String currentParentTag) {
//        if (!words[2].equals("..")) {
//            Node newNode = new Node(0, currentParentTag);
//            currentNodeTag = words[2];
//            fileSystem.put(words[3], newNode);
//
//            if (!words[2].equals("/")) {
//                currentParentTag = currentNodeTag;
//            }
//        } else {
//            currentNodeTag = currentParentTag;
//            assert currentNodeTag != null;
//            currentParentTag = fileSystem.get(currentNodeTag).getParent();
//        }
//    }
//
//    private void readListOutput(Tree tree, String nodeName) {
//        BufferedReader reader;
//        try {
//            reader = new BufferedReader(new FileReader(INPUT_FILE_PATH));
//            Node node = tree.get(nodeName);
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (line.isEmpty()) {
//                    continue;
//                }
//
//                String[] words = line.split(" ");
//                if (isCommand(words)) {
//                    return;
//                }
//
//                handleDirectoryOrFile(tree, node, words);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void handleDirectoryOrFile(Tree tree, Node node, String[] words) {
//        int dataSize = (words[0].equals("dir")) ? 0 : Integer.parseInt(words[0]);
//        Node newNode = new Node(dataSize, node.getName());
//        tree.put(words[1], newNode);
//        node.addChild(words[1]);
//    }
//
//}
