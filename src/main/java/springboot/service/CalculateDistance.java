/*
 * CalculateDistance involves in getting the current location and the phase of the delivery
 * without interfere directly with bing map and client's interest
 * it serves as helper functions to the WHICH? function
 * Shenyi Yu by 07/14/2019
 */

package springboot.service;

import org.json.JSONObject;
import springboot.dao.OptionDAO;
import springboot.external.BingMapAPI;
import springboot.model.DeliverOption;
import springboot.model.latlonGroup;

//uav input distance output location
//determine which phase uav and robot is in depending on their time

public class CalculateDistance {
    String phase;
    //in mph
    double UAVSpeed = 40;
    double RobotSpeed = 15;

    double currentDistance;
    double currentLat;
    double currentLon;
    double[] currentLocation;

    //get distance from time*speed and determine phase from time range
    public String DetectPhase(long currentTime, long dropoffTime, long leavingTime, long pickupTime,long returnTime){
        if (currentTime >= leavingTime && currentTime <= pickupTime) phase = "leaving";
        else if (currentTime > pickupTime && currentTime <= dropoffTime) phase = "pickup";
        else if (currentTime > dropoffTime && currentTime <= returnTime) phase = "return";
        else phase = "Phase not detected yet.";

        return phase;
    }





    public double[] DetectLocation(long currentTime,  DeliverOption uav, String trackid, String phase)
    {
        double[] start = getPhaseRoute(phase, trackid).getStart();
        double[] finish = getPhaseRoute(phase, trackid).getFinish();
        double[] current = getPhaseRoute(phase, trackid).getCurrent();



        if (uav.getTrackingid().substring(uav.getTrackingid().length() - 1).equals("R")){
            currentDistance = currentTime * 15;
        }
        else
            if (uav.getTrackingid().substring(uav.getTrackingid().length() - 1).equals("U")){
            currentDistance = currentTime * 40;
            //lat is the first index of the double array, while lon is the second.
                double latDistance = Math.toRadians(finish[0] - start[0]);
                double lonDistance = Math.toRadians(finish[1] - start[1]);
//                double a = GetLocation.UAVOption(37.73107, -122.40907, lat2, lon2);
//                double b = GetLocation.UAVOption(37.754452, -122.477165, lat2, lon2);
//                double c = GetLocation.UAVOption(37.782928, -122.418996, lat2, lon2);

                double totalDistance = GetLocation.UAVOption(start[0], start[1], finish[0], finish[1]);
                double ratio = currentDistance / totalDistance;
                currentLat = ratio * latDistance;
                currentLon = ratio * lonDistance;

                currentLocation = new double[] {currentLat, currentLon};
        }
        //-1 indicates something wrong with the trackingId
        else currentDistance = -1;


        return currentLocation;


    }

    /// call function ***getPhaseRoute(String Phase, String trackingid), which returns a latlonGroup
    public latlonGroup getPhaseRoute(String phase, String trackingid){
        return null;

    }
	

}
