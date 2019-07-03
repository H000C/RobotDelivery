/*
 * OptionServices involves in getting the delivery options
 * without interfere directly with bing map and client's interest
 * it serves as helper functions to the getOptions function
 */
package springboot.service;

import org.json.JSONObject;

import springboot.external.BingMapAPI;
import springboot.model.PointOnMap;


public class OptionService {
	
	// find the geometric distance between two points
	public static double distance(double lat1, double lat2, double lon1,
	        double lon2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000 ; 
	    long intermediate = Math.round(distance);
	    distance = (double) intermediate/1000;
	    return distance;
	}
	
	// find the travel distance necessary for unmanned aircraft
	public static double UAVOption(String location1, String location2){
		PointOnMap point1 = BingMapAPI.findLocation(location1);
		PointOnMap point2 = BingMapAPI.findLocation(location2);
		return distance(point1.getLat(), point2.getLat(), point1.getLon(),point2.getLon());
	}
	
	// find the travel distance necessary for walking robot
	public static double RobotOption(String location1, String location2) {
		JSONObject object = BingMapAPI.findRoute(location1, location2, false);
		if (!object.isNull("distance")) {
			return object.getDouble("distance");
		}
		return 0;
	}

}
