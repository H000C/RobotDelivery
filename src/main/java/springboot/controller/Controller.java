/*
 * Controller (Request Handler):
 *     Handles Get, Post requests from all url
 *     automatic conversion between jsonObject and object
 *     
 * Requests:
 *      Post /setOrder/setSender
 *      Post /setOrder/setRecipient
 *     
 * Created by: Haochen Liu
 * Modified by: Haochen Liu
 */
package springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.dao.RecipientDAO;
import springboot.dao.SenderDAO;
import springboot.model.Recipient;
import springboot.model.Sender;
import springboot.service.OrderService;

@RestController
public class Controller {
	
	@Autowired
	SenderDAO senderDao;
	
	@Autowired
	RecipientDAO recipientDao;
	
	/*
	 * saves an instance of sender into sender table
	 * returns the saved instance 
	 */
	@PostMapping("/setOrder/setSender")
	public Sender setSender(@Valid @RequestBody Sender sender) {
		if (sender.getOrderid() != null) return senderDao.save(sender);
		OrderService order = new OrderService();
		sender.setOrderid(order.generateOrder(sender));
		return senderDao.save(sender);
	}
	
	/*
	 * saves an instance of recipient into recipient table
	 * returns the saved instance 
	 */
	@PostMapping("/setOrder/setRecipient")
	public Recipient setRecipient(@Valid @RequestBody Recipient recipient) {
		if (recipient.getOrderid() != null) return recipientDao.save(recipient);
		OrderService order = new OrderService();
		recipient.setOrderid(order.generateOrder(recipient));
		return recipientDao.save(recipient);
	}
	
}
