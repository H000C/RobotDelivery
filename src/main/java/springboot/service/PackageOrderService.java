package springboot.service;

import org.springframework.stereotype.Service;
import springboot.model.Package;

import java.util.Date;

@Service
public class PackageOrderService {
	public String generateOrder(Package pa){
		String serviceType = pa.getServiceType();
		String size = pa.getSize();
		double weight = pa.getWeight();
		double cost = pa.getCost();//need further calculation, not just getter
		Date date = new Date();
		long time = date.getTime();
		String initial = pa.getCreatedBy();
		String orderid = initial + Long.toString(time);
		String trackingid = orderid + serviceType;
		return orderid;



//		this.serviceType = serviceType;
//		this.size = size;
//		this.shipDate = shipDate;
//		this.arrivalDate = arrivalDate;
//		this.weight = weight;
//		this.cost = cost;
//		this.createdBy = createdBy;
//		this.createdAt = createdAt;
	}




}
