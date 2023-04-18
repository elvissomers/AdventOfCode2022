package com.project.aoc;

import com.project.aoc.controllers.DayFive;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AocApplicationTests {

	DayFive testCase = new DayFive();
	@Test
	void getStacksSimpleTestOne() {
		String line1 = "    [D]    \n";
		String line2 = "[N] [C]    \n";
		String line3 = "[Z] [M] [P]";
		List<String> lines = Arrays.asList(line1, line2, line3);

		List<Deque<Character>> stacks = testCase.getStartStacks(lines);
		assertEquals(stacks.get(0).getLast(), 'Z');

	}

	@Test
	void getStacksSimpleTestTwo() {
		String line1 = "    [D]    \n";
		String line2 = "[N] [C]    \n";
		String line3 = "[Z] [M] [P]";
		List<String> lines = Arrays.asList(line1, line2, line3);

		List<Deque<Character>> stacks = testCase.getStartStacks(lines);
		assertEquals(stacks.get(1).pop(), 'D');

	}

	@Test
	void getStacksSimpleTestThree() {
		String line1 = "    [D]    \n";
		String line2 = "[N] [C]    \n";
		String line3 = "[Z] [M] [P]";
		List<String> lines = Arrays.asList(line1, line2, line3);

		List<Deque<Character>> stacks = testCase.getStartStacks(lines);
		assertEquals(stacks.get(1).getLast(), 'M');

	}

	@Test
	void getStacksSimpleTestFour() {
		String line1 = "    [D]    \n";
		String line2 = "[N] [C]    \n";
		String line3 = "[Z] [M] [P]";
		List<String> lines = Arrays.asList(line1, line2, line3);

		List<Deque<Character>> stacks = testCase.getStartStacks(lines);
		assertEquals(stacks.get(0).pop(), 'N');

	}

	@Test
	void getStacksSimpleTestFive() {
		String line1 = "    [D]    \n";
		String line2 = "[N] [C]    \n";
		String line3 = "[Z] [M] [P]";
		List<String> lines = Arrays.asList(line1, line2, line3);

		List<Deque<Character>> stacks = testCase.getStartStacks(lines);
		assertEquals(stacks.get(2).pop(), 'P');

	}
}
