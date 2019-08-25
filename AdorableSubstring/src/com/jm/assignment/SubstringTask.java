package com.jm.assignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstringTask extends RecursiveTask<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Regex to match given conditions for adorable string
	 **/
	private final static String ADOR_STRING_REGEX = "[a-z]+[a-z0-9:]*/[a-z0-9]+\\\\[a-z]+";
	private final static int THRESHOLD = 50;

	private int start;
	private int end;
	private String input;
	private int count;

	SubstringTask(int start, int end, String input) {
		this.start = start;
		this.end = end;
		this.input = input;
	}

	/**
	 * This method matches the given string with regex to check if given pattern
	 * is followed by string or not
	 * 
	 * @param word
	 * @return
	 */
	private boolean checkIfAdorable(String word) {
		Pattern pattern = Pattern.compile(ADOR_STRING_REGEX);
		Matcher matcher = pattern.matcher(word);
		if (matcher.matches()) {

			return true;
		} else
			return false;
	}

	@Override
	protected Integer compute() {
		List<SubstringTask> tasks = new ArrayList<>();

		if (end - start > THRESHOLD) {
			SubstringTask task = new SubstringTask(start, start + THRESHOLD, input);
			task.fork();
			tasks.add(task);
			SubstringTask task2 = new SubstringTask(start + THRESHOLD + 1, end, input);
			task2.fork();
			tasks.add(task2);

			int finalCount = addResultsFromTasks(tasks);
			return finalCount;
		} else {
			for (int i = start; i <= end; i++) {
				Set<String> uniqueAdorableSet = new HashSet<String>();
				for (int j = 5; j <= input.length() - i; j++) {
					String test = input.substring(i, i + j);

					if (checkIfAdorable(test)) {
						uniqueAdorableSet.add(test);

					}
				}

				if (uniqueAdorableSet.size() > 0) {
					count += uniqueAdorableSet.size();
				}
			}
			return count;
		}

	}

	private int addResultsFromTasks(List<SubstringTask> tasks) {
		int finalCount = 0;
		for (SubstringTask item : tasks) {
			finalCount += item.join();
		}
		return finalCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
