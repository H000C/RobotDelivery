/*
 * OrderService: services required to create an Order
 *
 * functionalities:
 *     generating order ID from sender or recipient information
 *
 * Created by: Haochen Liu
 * Modified by: Haochen Liu
 */

package springboot.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import springboot.model.Recipient;
import springboot.model.Sender;

@Service
public class OrderService {
	public static String generateOrder(Sender sender){
		Date date = new Date();
		long time = date.getTime();
		char initial = sender.getLastname().charAt(0);
		String orderid = initial + Long.toString(time);
		return orderid;
	}

	public static String generateOrder(Recipient recipient){
		Date date = new Date();
		long time = date.getTime();
		char initial = recipient.getLastname().charAt(0);
		String orderid = initial + Long.toString(time);
		return orderid;
	}

}
