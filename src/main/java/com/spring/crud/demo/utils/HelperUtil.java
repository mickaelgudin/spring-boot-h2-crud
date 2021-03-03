package com.spring.crud.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.util.Arrays;

import java.util.List;
import java.util.function.Supplier;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.spring.crud.demo.model.TrainStation;

public class HelperUtil {

    private HelperUtil() {
    }


    public static Supplier<List<TrainStation>> trainStationsSupplier = () ->
    
            Arrays.asList(
		            getTrainVersaillesChantiers()
            );
            
            
    /**
     * method providing train stations with nativia api
     * @return
     */
    public static TrainStation getTrainVersaillesChantiers() {
    	JSONObject json = HelperUtil.getResponseFromUrl("https://api.navitia.io/v1/coverage/fr-idf/places?q=Gare%20de%20Versailles%20Chantiers&type%5B%5D=stop_area&");
		
    	if(json != null) {
			//we can not access json property - here we get an array places
	        JSONArray places = (JSONArray) json.get("places");
	        
	        JSONObject placeOne = (JSONObject) places.get(0);
	        JSONObject stopArea = (JSONObject) placeOne.get("stop_area");
	        JSONObject coord = (JSONObject) stopArea.get("coord");
	        return new TrainStation(String.valueOf(stopArea.get("name")), Double.valueOf(String.valueOf(coord.get("lon")) ), Double.valueOf(String.valueOf(coord.get("lat")) ));
    	}
        
        return new TrainStation();
    }
    
    public static JSONObject getResponseFromUrl(String urlOfEndpoint) {
    	URL url = null;
		try {
			url = new URL(urlOfEndpoint);
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}
        HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

        conn.setRequestProperty("Authorization", "eabc7e82-7f1f-42b9-b9d2-c8cc477b7bdd");
        
        conn.setRequestProperty("Content-Type","application/json");
        try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e1) {
			e1.printStackTrace();
		}


        BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        String output;

        StringBuffer response = new StringBuffer();
        try {
			while ((output = in.readLine()) != null) {
			    response.append(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

        try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        JSONParser parser = new JSONParser(); 
        JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(response.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return json;
    }

}
