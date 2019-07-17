/*
 * CalculateDistance involves in getting the current location and the phase of the delivery
 * without interfere directly with bing map and client's interest
 * it serves as helper functions to the WHICH? function
 * Shenyi Yu by 07/14/2019
 */

package springboot.service;

import org.json.JSONObject;

import springboot.external.BingMapAPI;
import springboot.model.DeliverOption;

//uav input distance output location
//determine which phase uav and robot is in depending on their time

public class CalculateDistance {
    String phase;
    //in mph
    double UAVSpeed = 40;
    double RobotSpeed = 30;

    double currentDistance;
    double currentLat;
    double currentLon;
    double[] currentLocation;

    //get distance from time*speed and determine phase from time range
    public String DetectPhase(int currentTime){
        if (currentTime >= 0 && currentTime < 1000) phase = "drop off";
        else if (currentTime >= 10 && currentTime < 20) phase = "leaving";
        else if (currentTime >= 20 && currentTime < 30) phase = "pickup";
        else if (currentTime >= 30 && currentTime < 40) phase = "return";
        else phase = "Phase not detected yet.";

        return phase;
    }


    public double[] DetectLocation(int currentTime, double lat1, double lon1, DeliverOption uav, double lat2, double lon2)
    {

        if (uav.getTrackingid().substring(uav.getTrackingid().length() - 1).equals("R")){
            currentDistance = currentTime * 30;
        }
        else if (uav.getTrackingid().substring(uav.getTrackingid().length() - 1).equals("U")){
            currentDistance = currentTime * 40;
        }
        //-1 indicates something wrong with the trackingId
        else currentDistance = -1;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = GetLocation.UAVOption(37.73107, -122.40907, lat2, lon2);
//        double b = GetLocation.UAVOption(37.754452, -122.477165, lat2, lon2);
//        double c = GetLocation.UAVOption(37.782928, -122.418996, lat2, lon2);

        double ratio = currentDistance / a;
        currentLat = ratio * latDistance;
        currentLon = ratio * lonDistance;

        currentLocation = new double[] {currentLat, currentLon};
        return currentLocation;


    }
	

}
