package springboot.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import springboot.model.Recipient;
import springboot.model.Sender;

@Service
public class OrderService {
	public String generateOrder(Sender sender){
		Date date = new Date();
		long time = date.getTime();
		String initial = sender.getLastname();
		String orderid = initial + Long.toString(time);
		return orderid;
	}
	
	public String generateOrder(Recipient recipient){
		Date date = new Date();
		long time = date.getTime();
		String initial = recipient.getLastname();
		String orderid = initial + Long.toString(time);
		return orderid;
	}

}
