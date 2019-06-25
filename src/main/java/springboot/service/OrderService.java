/*
 * OrderService:
 *     provide logic functions necessary for ordering shipping
 *     
 * Functionalities:
 *     generating order id with a given sender recipient
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
	
	// generating order id
	public String generateOrder(Sender sender){
		Date date = new Date();
		long time = date.getTime();
		String initial = sender.getLastname();
		String orderid = initial + Long.toString(time);
		return orderid;
	}
	
	// generating order id
	public String generateOrder(Recipient recipient){
		Date date = new Date();
		long time = date.getTime();
		String initial = recipient.getLastname();
		String orderid = initial + Long.toString(time);
		return orderid;
	}

}
