package com.project.aoc;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class AocApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(AocApplication.class, args);
//	}
//
//}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AocApplication {
	public static void main(String[] args) {
		String inputFilePath = "C:\\Users\\elvis\\projects\\aoc_new\\data\\scraped_data_1.txt";
		ArrayList<Integer> calories = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					calories.add(Integer.parseInt(line.trim()));
				} else {
					calories.add(-1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		PriorityQueue<Integer> topCalories = new PriorityQueue<>();
		int currentCalories = 0;
		for (Integer calorie : calories) {
			if (calorie == -1) {
				if (topCalories.size() < 3) {
					topCalories.add(currentCalories);
				} else if (currentCalories > topCalories.peek()) {
					topCalories.poll();
					topCalories.add(currentCalories);
				}
				currentCalories = 0;
			} else {
				currentCalories += calorie;
			}
		}
		if (topCalories.size() < 3 || currentCalories > topCalories.peek()) {
			topCalories.poll();
			topCalories.add(currentCalories);
		}

		int totalCalories = topCalories.stream().mapToInt(Integer::intValue).sum();
		System.out.println(totalCalories);
	}
}
