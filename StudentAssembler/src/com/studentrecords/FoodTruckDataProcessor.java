package com.studentrecords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FoodTruckDataProcessor {

	private static final String sURL = "https://data.sfgov.org/resource/6a9r-agq8.json";

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public JsonObject loadJson123() throws IOException {
		URL url = new URL(sURL);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		// Convert to a JSON object to print data
		JsonParser jp = new JsonParser(); // from gson
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
		JsonObject rootobj = root.getAsJsonObject();
		JsonArray jsonArray = rootobj.getAsJsonArray("");
		rootobj = jsonArray.get(0).getAsJsonObject();
		System.out.println(rootobj.get("fooditems"));
		return rootobj;
	}

	public List<FoodDataDao> loadJson() {

		String json = null;
		List<FoodDataDao> foodDataList = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			URL url = new URL(sURL);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();
			bufferedReader = new BufferedReader(new InputStreamReader((InputStream) request.getContent()));
			StringBuffer buffer = new StringBuffer();
			char[] dataLength = new char[656353];

			int read;
			while ((read = bufferedReader.read(dataLength)) != -1) {
				buffer.append(dataLength, 0, read);
			}
			json = buffer.toString();
			try {
				JSONArray jsonArray = new JSONArray(json);
				System.out.println("length is: " + jsonArray.length());
				Gson gson = new Gson();
				for (int i = 0; i < 5; i++) {
					FoodDataDao foodData = gson.fromJson(jsonArray.getJSONObject(i).toString(), FoodDataDao.class);
					if (foodData != null && "Truck".equals(foodData.getFacilitytype())) {
						foodDataList.add(foodData);
					}
				}

				System.out.println("List size: " + foodDataList.size());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return foodDataList;
	}
}
