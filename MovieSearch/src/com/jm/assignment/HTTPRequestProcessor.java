package com.jm.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class represents HTTP Request processing logic for given URL. This class
 * processes the given URL and return a string response.
 * 
 * @author Kajal
 *
 */
public class HTTPRequestProcessor {

	/**
	 * This class takes URL as input and process it using HTTP GET type by and
	 * return the string response.
	 * 
	 * @param dataUrl
	 * @return
	 */
	public String getResponse(String dataUrl) {

		String json = null;
		URL url;
		try {
			url = new URL(dataUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			if (response != null) {
				json = response.toString();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return json;
	}
}
