/*
 * OrderController (Request Handler):
 *     Handles Get, Post requests from order related tasks
 *     automatic conversion between jsonObject and object
 *     
 * Requests:
 *      Post /setOrder/setSender (return Sender)
 *      Post /setOrder/setRecipient (return Recipient)
 *      Get /trackOrder (return HTML)
 *     
 * Created by: Haochen Liu
 * Modified by: Haochen Liu
 */
package springboot.controller;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springboot.dao.OptionDAO;
import springboot.dao.RecipientDAO;
import springboot.dao.SenderDAO;
import springboot.model.DeliverOption;
import springboot.model.Recipient;
import springboot.model.Sender;
import springboot.service.EmailService;
import springboot.service.HistoryService;
import springboot.service.OptionService;
import springboot.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	SenderDAO senderDao;
	
	@Autowired
	RecipientDAO recipientDao;

	@Autowired
	HistoryService historyService;

	@Autowired
	EmailService emailService;
	
	/*
	 * saves an instance of sender into sender table
	 * returns the saved instance 
	 */
	@PostMapping("/setOrder/setSender")
	@ResponseBody
	public Sender setSender(@Valid @RequestBody Sender sender) {
		if (sender.getOrderid() != null) return senderDao.save(sender);
		String orderId = OrderService.generateOrder(sender);
		sender.setOrderid(orderId);
		//historyService.setHistory(sender.getUsername(), orderId, 1);
		//emailService.sendTrackingId(sender.getEmail(), orderId);
		return senderDao.save(sender);
	}
	
	/*
	 * saves an instance of recipient into recipient table
	 * returns the saved instance 
	 */
	@PostMapping("/setOrder/setRecipient")
	@ResponseBody
	public Recipient setRecipient(@Valid @RequestBody Recipient recipient) {
		if (recipient.getOrderid() != null) return recipientDao.save(recipient);
		recipient.setOrderid(OrderService.generateOrder(recipient));
		return recipientDao.save(recipient);
	}
	
	/*
	 * returns a HTML page under templates
	 * which name is TrackOrder
	 */
	@RequestMapping("/trackOrder")
	public String mapWebControl() {
		return "TrackOrder";
	}
	
}
