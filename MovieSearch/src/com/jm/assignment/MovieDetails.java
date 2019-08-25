package com.jm.assignment;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

/**
 * This class is used to query the given URL to fetch movie details.
 * 
 * @author Kajal
 */
public class MovieDetails {

	private static final String URL = "https://jsonmock.hackerrank.com/api/movies/search?Title=";

	public static void main(String args[]) {
		Scanner src = new Scanner(System.in);
		MovieDetails movieDetails = new MovieDetails();
		System.out.println("Enter substring to query");
		String substr = src.nextLine();
		src.close();
		substr = substr.trim();
		movieDetails.getMovieTitles(substr);

	}

	/**
	 * This method is used to get movie titles. It first queries details from
	 * json by passing given substring.<br>
	 * From the json response total pages to query are read and then method
	 * queries all the pages to get movie titles containing given substring.<br>
	 * To read different pages in parallel so as to improve the speed, TheadPool
	 * is created using ExecutorService.<br>
	 * Json loading for each page is passed as a task to pool so that threads
	 * process them concurrently and add the titles in TreeSet- movieDetails
	 * 
	 * @param substr
	 */
	public void getMovieTitles(String substr) {
		TreeSet<String> movieTitles = new TreeSet<>();
		Collections.synchronizedSet(movieTitles);
		HTTPRequestProcessor processor = new HTTPRequestProcessor();
		String jsonString = processor.getResponse(URL + substr);

		Gson gson = new Gson();

		MovieDetailsDao movieDetailsDao = gson.fromJson(jsonString, MovieDetailsDao.class);
		int totalPages = movieDetailsDao.getTotal_pages();
		int threadPoolSize = totalPages / 10;
		ExecutorService executor = Executors.newFixedThreadPool(++threadPoolSize);
		for (int page = 1; page <= totalPages; page++) {
			ProcessRequestRunnable runnable = new ProcessRequestRunnable(substr, page, movieTitles);
			executor.submit(runnable);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("size " + movieTitles.size() + " " + movieTitles);

	}

}
