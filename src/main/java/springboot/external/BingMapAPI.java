/*
 * BingMapAPI: directly contact the bing map rest services
 *     findLocation: find latitude longitude of location if address is valid
 *     findRoute: find overview/detail of the walking route between two locations
 */
package springboot.external;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import springboot.ProjectConstants;
import springboot.model.PointOnMap;

@Service
public class BingMapAPI {
	private static final String URL = "http://dev.virtualearth.net/REST/v1/";
	private static final String DEFAULT_KEYWORD = ""; // no restriction
	private static final String API_KEY = ProjectConstants.BingMapKey;
	// takes in a wordy address, return a point on map
	public static PointOnMap findLocation(String address) {
		String location = address;
		if (location == null) {
			location = "Locations/";
		}
		try {
			location = "Locations/" + URLEncoder.encode(location, "UTF-8"); //"Rick Sun" => "Rick%20Sun"
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = URL + location + "?o=json&key=" + API_KEY;
		
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			
			int responseCode = connection.getResponseCode();
			System.out.println("Sending request to url: " + url);
			System.out.println("Response code: " + responseCode);
			
			if (responseCode != 200) {
				return new PointOnMap(false, "invalid address", 0, 0);
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			JSONObject obj = new JSONObject(response.toString());
			
			boolean validation = false;
			JSONArray coordinates = new JSONArray();
			
			if (!obj.isNull("resourceSets")) {
				
				JSONArray resourceSet = obj.getJSONArray("resourceSets");
				if(!resourceSet.isNull(0) && !(resourceSet.getJSONObject(0)).isNull("resources")) {
					
					JSONArray resource = resourceSet.getJSONObject(0).getJSONArray("resources");
					if(!resource.isNull(0) && !(resource.getJSONObject(0)).isNull("point") && !(resource.getJSONObject(0)).isNull("confidence")) {
						String buffer = resource.getJSONObject(0).getString("confidence");
						if ( buffer.equals("High")) validation = true;
						JSONObject point = resource.getJSONObject(0).getJSONObject("point");
						if(!point.isNull("coordinates")) {
							coordinates = point.getJSONArray("coordinates");
							return new PointOnMap(validation , address, coordinates.getDouble(0), coordinates.getDouble(1));
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new PointOnMap(false, "invalid address", 0, 0);
	}
	
	/* takes 2 address and a enabling boolean, return information of a route as a JsonObject
	 * {
	 *     "distance": 10.0 (double),
	 *     "directions" : ... (JsonArray), (see bing map routeLeg)
	 *     "valid" : true (boolean) (whether the operation is valid)
	 * }
	 */
	public static JSONObject findRoute(String address1, String address2, boolean detail) {
		
		if (address1 == null || address2 == null) {
			address1 = address2 = DEFAULT_KEYWORD;
			return null;
		}
		
		try {
			address1 = URLEncoder.encode(address1, "UTF-8"); //"Rick Sun" => "Rick%20Sun"
			address2 = URLEncoder.encode(address2, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query = String.format("wp.0=%s&wp.1=%s&optmz=distance&output=json&key=%s",address1, address2, API_KEY );
		String url = URL + "Routes/Walking?" + query;
		
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			
			int responseCode = connection.getResponseCode();
			System.out.println("Sending request to url: " + url);
			System.out.println("Response code: " + responseCode);
			
			if (responseCode != 200) {
				JSONObject error = new JSONObject();
				error.put("valid", false);
				return error;
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			JSONObject obj = new JSONObject(response.toString());
			JSONObject new_obj = new JSONObject();
			
			if (!obj.isNull("resourceSets")) {
				
				JSONArray resourceSet = obj.getJSONArray("resourceSets");
				if(!resourceSet.isNull(0) && !(resourceSet.getJSONObject(0)).isNull("resources")) {
					JSONArray resource = resourceSet.getJSONObject(0).getJSONArray("resources");
					if(!resource.isNull(0) && !(resource.getJSONObject(0)).isNull("routeLegs") && !(resource.getJSONObject(0)).isNull("travelDistance")) {
						if(detail) new_obj.put("directions", resource.getJSONObject(0).getJSONArray("routeLegs"));
						new_obj.put("distance", resource.getJSONObject(0).getDouble("travelDistance"));
						new_obj.put("valid", true);
						return new_obj;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject error = new JSONObject();
		error.put("valid", false);
		return error;
	}
}
