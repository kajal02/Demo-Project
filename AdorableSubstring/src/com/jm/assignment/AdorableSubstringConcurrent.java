package com.jm.assignment;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents implementation to count how many substrings from a
 * given set of strings are considered adorable.<br>
 * String is considered adorable if following conditions are satisfied:<br>
 * <ul>
 * <li>The first letter of the string is a lowercase English letter.</li>
 * <li>Next, it contains a sequence of zero or more of the following characters:
 * lowercase English letters, digits, and colons.</li>
 * <li>Next, it contains a forward slash.</li>
 * <li>Next, it contains a sequence of one or more of the following characters:
 * lowercase English letters and digits.</li>
 * <li>Next, it contains a backward slash.</li>
 * <li>Next, it contains a sequence of one or more lowercase English
 * letters.</li>
 * </ul>
 * 
 * @author Kajal
 **/
public class AdorableSubstringConcurrent {

	public static void main(String[] args) throws InterruptedException, IOException {
		Scanner src = new Scanner(System.in);
		System.out.println("Enter number of input strings");
		int n = src.nextInt();
		if (n < 1 || n > 50) {
			src.close();
			throw new IOException("Number of strings should be between 1 and 50 only");
		}
		String input[] = new String[n];
		int output[] = new int[n];
		src.nextLine();
		System.out.println("Enter " + n + " strings: ");
		for (int i = 0; i < n; i++) {
			input[i] = src.nextLine();
			if (input[i].length() > 5_10_000) {
				src.close();
				throw new IOException("Maximum string length exceeded.");
			}
		}
		src.close();

		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < n; i++) {
			AdorableSubstringRunnable task = new AdorableSubstringRunnable(input[i], output, i);
			service.submit(task);
		}
		service.shutdown();
		while (!service.isTerminated()) {
		}

		System.out.println(Arrays.toString(output));
	}

}
