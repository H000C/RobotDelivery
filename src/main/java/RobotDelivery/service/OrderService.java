package RobotDelivery.service;

import java.sql.Timestamp;

public class OrderService {
	public static String idGenerator(String lastname) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long time = timestamp.getTime();
		String orderid = lastname + Long.toString(time);
		return orderid;
	}

}
