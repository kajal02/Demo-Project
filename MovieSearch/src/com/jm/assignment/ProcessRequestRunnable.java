package com.jm.assignment;

import java.util.TreeSet;

import com.google.gson.Gson;

public class ProcessRequestRunnable implements Runnable {
	/**
	 * URL to query movie details
	 */
	private static final String URL = "https://jsonmock.hackerrank.com/api/movies/search?Title=";

	String substr;
	int page;
	TreeSet<String> movieTitles;

	/**
	 * 
	 * @param substr
	 * @param page
	 * @param movieTitles
	 */
	ProcessRequestRunnable(String substr, int page, TreeSet<String> movieTitles) {
		this.substr = substr;
		this.page = page;
		this.movieTitles = movieTitles;
	}

	@Override
	public void run() {
		/*
		 * This method queries given url with substring and required page number
		 */
		HTTPRequestProcessor processor = new HTTPRequestProcessor();
		String jsonString = processor.getResponse(URL + substr + "&page=" + page);
		addMovieFromJson(jsonString, movieTitles);
	}

	/**
	 * This method is used to process given Json string using GSON library, to
	 * convert and map json parameters to list of Movie.<br>
	 * It then adds movie titles in TreeSet so that they are sorted by
	 * alphabetical order.
	 * 
	 * @param jsonString
	 * @param movieTitles
	 */
	private void addMovieFromJson(String jsonString, TreeSet<String> movieTitles) {
		Gson gson = new Gson();
		MovieDetailsDao movieDetailsDao = gson.fromJson(jsonString, MovieDetailsDao.class);
		if (movieDetailsDao != null && movieDetailsDao.getData() != null) {
			for (MovieDao movie : movieDetailsDao.getData()) {
				movieTitles.add(movie.getTitle());
			}
		}
	}
}
