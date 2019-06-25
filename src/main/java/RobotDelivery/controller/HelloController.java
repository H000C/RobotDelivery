package RobotDelivery.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import RobotDelivery.model.OrderId;
import RobotDelivery.model.Person;
import RobotDelivery.service.OrderService;


@RestController
public class HelloController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public OrderId setHello(@RequestBody Person sender) {
		String orderid = OrderService.idGenerator(sender.getLastname());
		return new OrderId(orderid);
	}

}
