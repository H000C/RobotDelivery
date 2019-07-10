package springboot.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import springboot.dao.SummaryDAO;
import springboot.model.DeliverOption;
import springboot.model.DeliverSummary;

@Service
public class DeliveryService {
	private static final double ROBOT_SPEED = 15;
	private static final double UAV_SPEED = 40;
	
	// set a deliver option by returning an option with arriving time
	public static DeliverOption setDelivery(DeliverOption option) {
		
		double deliverTime = 0;
		double delivery_distance = option.getInitialDistance() + option.getDeliveryDistance();
		deliverTime = findDeliverTime(delivery_distance, option);
		if (deliverTime == 0.0) return null;
		
		Calendar cal = Calendar.getInstance();
	    UpdateCalendar(deliverTime, cal);
	    Date date = cal.getTime();
	    
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    // setting arriving time
	    option.setArrivingTime(sdfDate.format(date));
		return option;
	}
	
	// preparing the option's deliver summary, this will make tracking easier
	// deliver summary will be saved in database
	public static void setDeliverSummery(DeliverOption option, SummaryDAO summary) {
		
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DeliverSummary delisum = new DeliverSummary();
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		
		delisum.setTrackingid(option.getTrackingid());
		delisum.setLeavingTime(date.getTime());
		
		double deliverTime = 0;
		double delivery_distance = option.getInitialDistance();
		deliverTime = findDeliverTime(delivery_distance, option);
		date = UpdateCalendar(deliverTime, cal).getTime();
		delisum.setPickupTime(date.getTime());
		
		delivery_distance = option.getDeliveryDistance();
		deliverTime = findDeliverTime(delivery_distance, option);
		date = UpdateCalendar(deliverTime, cal).getTime();
		delisum.setDropoffTime(date.getTime());
		
		delivery_distance = option.getReturnDistance();
		deliverTime = findDeliverTime(delivery_distance, option);
		date = UpdateCalendar(deliverTime, cal).getTime();
		delisum.setReturnTime(date.getTime());
		
		summary.save(delisum);
	}
	
	// determine travelling speed by deliver option
	public static double findDeliverTime(double delivery_distance, DeliverOption option) {
		double deliverTime = 0;
		if (option.getDeliveryOption().equals("UAV")) {
			deliverTime = delivery_distance/UAV_SPEED;
		}
		else if (option.getDeliveryOption().equals("Robot")){
			deliverTime = delivery_distance/ROBOT_SPEED;
		}
		return deliverTime;
	}
	
	// add deliver time to the calendar, deliverTime in hours
	public static Calendar UpdateCalendar(double deliverTime, Calendar cal) {
		int hour = (int)Math.round(deliverTime);
		double MIS = (deliverTime - hour)*60;
		int minute = (int)Math.round(MIS);
		double SMS = (MIS - minute)*60;
		int second = (int)Math.round(SMS);
		cal.add(Calendar.SECOND, second); // adds seconds
	    cal.add(Calendar.MINUTE, minute); // adds minutes
	    cal.add(Calendar.HOUR_OF_DAY, hour); // adds hours
		return cal;
	}
}
