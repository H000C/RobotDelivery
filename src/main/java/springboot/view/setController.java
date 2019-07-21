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

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class setController {
	/*
	 * returns a HTML page under templates
	 */
	@RequestMapping("/")
	public String indexControl() {
		return "newIndex";
	}
	@RequestMapping("/shipSender")
	public String shipSenderControl() {
		return "shipSender";
	}
	@RequestMapping("/shipReceiver")
	public String shipReceiverControl() {
		return "shipReceiver";
	}
	@RequestMapping("/shipMethod")
	public String shipMethodControl() {
		return "shipMethod";
	}
	@RequestMapping("/tracking")
	public String trackingControl() {
		return "tracking";
	}
}