package com.jm.assignment;

import java.util.concurrent.ForkJoinPool;

public class AdorableSubstringRunnable implements Runnable {

	String input;
	int[] output;
	int request;

	/**
	 * 
	 * @param input
	 * @param output
	 * @param request
	 */
	AdorableSubstringRunnable(String input, int[] output, int request) {
		this.input = input;
		this.output = output;
		this.request = request;
	}

	@Override
	public void run() {
		/*
		 * This method checks a count of all possible adorable substrings of a
		 * given string.<br> It matches the given regex with to check if
		 * conditions to be adorable are followed or not.
		 */

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		SubstringTask callable = new SubstringTask(0, input.length() - 5, input);
		int count = forkJoinPool.invoke(callable);
		output[request] = count;

	}

}
