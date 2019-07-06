package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.dao.RecipientDAO;
import springboot.dao.SenderDAO;
import springboot.model.DeliverOption;
import springboot.model.Recipient;
import springboot.model.Sender;
import springboot.model.StationNDistance;

@Service
public class OptionService {
	
	static String station1 = "1390 Silver Ave, San Francisco, CA";
	static double[] station1_latlon = {37.73107, -122.40907};
	static String station2 = "1222 Noriega St, San Francisco, CA";
	static double[] station2_latlon = {37.754452, -122.477165};
	static String station3 = "652 Polk St, San Francisco, CA";
	static double[] station3_latlon = {37.782928, -122.418996};
	
	public static DeliverOption[] getOptions (String order, SenderDAO senderDao, RecipientDAO recipientDao) {
		
		Sender sender = senderDao.findOne(order);
		Recipient recipient = recipientDao.findOne(order);
		if (sender == null || recipient == null) {
			return null;
		}
		System.out.println("there is sender/recipient information");
		if (sender.getAddress().equals("invalid address") || recipient.getAddress().equals("invalid address")) {
			return null;
		}
		System.out.println("there is valid location information");
		StationNDistance begin = closestStation(sender.getLatitude(), sender.getLongitude());
		StationNDistance end = closestStation(recipient.getLatitude(), recipient.getLongitude());
		
		DeliverOption uav = new DeliverOption();
		uav.setTrackingid(order + "U");
		uav.setDeliveryOption("UAV");
		uav.setStartStation(begin.stationNum);
		uav.setEndStation(end.stationNum);
		uav.setPickupLocation(sender.getAddress());
		uav.setDropoffLoaction(recipient.getAddress());
		uav.setInitialDistance(begin.distance);;
		uav.setDeliveryDistance(
				CalculateDistance.UAVOption(
						sender.getLatitude(), sender.getLongitude(), recipient.getLatitude(), recipient.getLongitude()));
		uav.setReturnDistance(end.distance);
		
		DeliverOption robot = new DeliverOption();
		robot.setTrackingid(order + "R");
		robot.setDeliveryOption("Robot");
		robot.setStartStation(begin.stationNum);
		robot.setEndStation(end.stationNum);
		robot.setPickupLocation(sender.getAddress());
		robot.setDropoffLoaction(recipient.getAddress());
		robot.setInitialDistance(CalculateDistance.RobotOption(begin.stationAddr, sender.getAddress()));
		robot.setDeliveryDistance(CalculateDistance.RobotOption(sender.getAddress(), recipient.getAddress()));
		robot.setReturnDistance(CalculateDistance.RobotOption(recipient.getAddress(), end.stationAddr));
		DeliverOption[] twoOptions = {robot, uav};
		return twoOptions;
	}
	
	public static StationNDistance closestStation (double latitude, double longitude) {
		double a = CalculateDistance.UAVOption(37.73107, -122.40907, latitude, longitude);
		double b = CalculateDistance.UAVOption(37.754452, -122.477165, latitude, longitude);
		double c = CalculateDistance.UAVOption(37.782928, -122.418996, latitude, longitude);
		StationNDistance station = new StationNDistance();
		if (a <= b && a <= c) {
			station.distance = a;
			station.stationNum = 1;
			station.stationAddr = station1;
		    return station;
		} else if (b <= c && b <= a) {
			station.distance = b;
			station.stationNum = 2;
			station.stationAddr = station2;
		    return station;
		} else {
			station.distance = c;
			station.stationNum = 3;
			station.stationAddr = station3;
		    return station;
		}
	}

}
