package springboot.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import springboot.ProjectConstants;
import springboot.external.BingMapAPI;
import springboot.model.DeliverOption;
import springboot.model.latlonGroup;

@Service
public class CDHelper {

    public static latlonGroup getPhaseRoute(String Phase, DeliverOption option) {
        latlonGroup llg = new latlonGroup();
        if (Phase.equals("leaving")) {
            llg.setStart(getStationCoord(option.getStartStation()));
            llg.setStartAddr(getStationAddr(option.getStartStation()));
            llg.setFinish(option.getPickupLatLon());
            llg.setFinishAddr(option.getPickupLocation());
            llg.setTraveled(0);
            llg.setPhase("leaving");
        }
        else if (Phase.equals("pickup")) {
            llg.setStart(option.getPickupLatLon());
            llg.setStartAddr(option.getPickupLocation());
            llg.setFinish(option.getDropoffLatLon());
            llg.setFinishAddr(option.getDropoffLoaction());
            llg.setTraveled(option.getInitialDistance());
            llg.setPhase("delivering");
        }
        else if (Phase.equals("return")){
            llg.setStart(option.getDropoffLatLon());
            llg.setStartAddr(option.getDropoffLoaction());
            llg.setFinish(getStationCoord(option.getEndStation()));
            llg.setFinishAddr(getStationAddr(option.getEndStation()));
            llg.setTraveled(option.getInitialDistance() + option.getDeliveryDistance());
            llg.setPhase("returning");
        }
        else {
            llg.setStart(option.getDropoffLatLon());
            llg.setStartAddr(option.getDropoffLoaction());
            llg.setCurrent(getStationCoord(option.getEndStation()));
            llg.setFinish(getStationCoord(option.getEndStation()));
            llg.setFinishAddr(getStationAddr(option.getEndStation()));
            llg.setTraveled(option.getInitialDistance() + option.getDeliveryDistance());
            llg.setPhase("finished");
        }
        return llg;
    }

    private static double[] getStationCoord(int id) {
        if (id == 1) {
            return ProjectConstants.station1_latlon;
        }
        else if (id == 2) {
            return ProjectConstants.station2_latlon;
        }
        else if (id == 3) {
            return ProjectConstants.station3_latlon;
        }
        return null;
    }

    private static String getStationAddr(int id) {
        if (id == 1) {
            return ProjectConstants.station1;
        }
        else if (id == 2) {
            return ProjectConstants.station2;
        }
        else if (id == 3) {
            return ProjectConstants.station3;
        }
        return null;
    }

    public static latlonGroup getRobotTracking(latlonGroup track, double distance) {
        JSONObject object = BingMapAPI.findRoute(track.getStartAddr(), track.getFinishAddr(), true);
        JSONArray route = null;
        JSONObject point = null;
        if (!object.isNull("directions")) {
            JSONObject open = object.getJSONArray("directions").getJSONObject(0);
            System.out.println("opened");
            if(!open.isNull("itineraryItems")) {
                route = open.getJSONArray("itineraryItems");
            }
        }
        System.out.println(route.toString());
        for(int i = 0; i < route.length(); i++) {
            JSONObject routeObj = route.getJSONObject(i);
            if(!routeObj.isNull("travelDistance")) {
                double distance_traveled = routeObj.getDouble("travelDistance");
                distance -= distance_traveled;
                if (distance <= 0) {
                    point = routeObj.getJSONObject("maneuverPoint");
                    break;
                }
                if (i == route.length() - 1) {
                    point = routeObj.getJSONObject("maneuverPoint");
                }
            }
        }
        JSONArray coordinate = point.getJSONArray("coordinates");
        System.out.println(point.toString());
        double[] latlon = {coordinate.getDouble(0), coordinate.getDouble(1)};
        track.setCurrent(latlon);
        return track;
    }
}
