/*
 * Controller (Request Handler):
 *     Handles Get, Post requests from all url
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
package springboot.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import springboot.dao.RecipientDAO;
import springboot.dao.SenderDAO;

@Controller
public class setController {

	@Autowired
	SenderDAO senderDao;

	@Autowired
	RecipientDAO recipientDao;

	/*
	 * returns a HTML page under templates
	 */
	@RequestMapping("/")
	public String indexControl() {
		return "index";
	}
	@RequestMapping("/shipSender")
	public String shipSenderControl() {
		return "shipSender";
	}
	@RequestMapping("/shipReceiver")
	public String shipReceiverControl() {
		return "shipReceiver";
	}
	@RequestMapping("/shipPackage")
	public String shipPackageControl() {
		return "shipPackage";
	}
	@RequestMapping("/shipMethod")
	public String shipMethodControl() {
		return "shipMethod";
	}
	@RequestMapping("/shipSuccess")
	public String shipSuccessControl() {
		return "shipSuccess";
	}
	@RequestMapping("/tracking")
	public String trackingControl() {
		return "tracking";
	}
}