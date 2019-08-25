package com.jm.assignment;

public class MovieDao {
	/**
	 * Poster link for the movie
	 */
	private String Poster;
	/**
	 * Title of the movie
	 */
	private String Title;
	/**
	 * Movie type
	 */
	private String Type;
	/**
	 * Movie release year
	 */
	private int Year;
	/**
	 * IMDB id for movie
	 */
	private String imdbID;

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String poster) {
		Poster = poster;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

}
