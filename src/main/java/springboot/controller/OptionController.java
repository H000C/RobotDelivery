/*
 * OptionController (Request Handler):
 *     Handles Get, Post requests from option related tasks
 *     automatic conversion between jsonObject and object
 *     
 * Requests:
 *      Post /setOrder/getOption (return Option)
 *      Post /setOrder/setOption (return Option, with deliver time)
 *     
 * Created by: Haochen Liu
 * Modified by: Haochen Liu
 */
package springboot.controller;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import springboot.dao.OptionDAO;
import springboot.dao.RecipientDAO;
import springboot.dao.SenderDAO;
import springboot.dao.SummaryDAO;
import springboot.model.DeliverOption;
import springboot.service.DeliveryService;
import springboot.service.OptionService;

@Controller
public class OptionController {
	
	@Autowired
	SenderDAO senderDao;
	
	@Autowired
	RecipientDAO recipientDao;
	
	@Autowired
	OptionDAO optionDao;
	
	@Autowired
	SummaryDAO summaryDao;

	// returns one option with robot and one option ith uav
	@PostMapping ("/setOrder/getOptions")
	@ResponseBody
	public DeliverOption[] getOptions (@Valid @RequestBody String orderid) {
		if (orderid == null) {
			System.out.println("Umm... No order id");
			return null;
		}
		JSONObject object = new JSONObject(orderid);
		System.out.println("Getting Options for " + object.getString("orderid"));
		DeliverOption[] options = OptionService.getOptions(object.getString("orderid"), senderDao, recipientDao);
		for(int i = 0; i < options.length; i++) {
			if (options[i] == null) return null;
			optionDao.save(options[i]);
		}
		return options;
	}
	
	// returns the selected Option, with a approximate deliver time
	@PostMapping ("/setOrder/setOptions")
	@ResponseBody
	public DeliverOption setOption (@Valid @RequestBody String trackingid) {
		if (trackingid == null) {
			System.out.println("Umm... No tracking id");
			return null;
		}
		JSONObject object = new JSONObject(trackingid);
		System.out.println("Setting Option with " + object.getString("trackingid"));
		String id = object.getString("trackingid");
		char[] c = id.toCharArray();
		if (c[c.length -1] == 'U') {
			c[c.length -1] = 'R';
		}
		else if (c[c.length -1] == 'R'){
			c[c.length -1] = 'U';
		}
		else return null;
		String delid = new String(c);
		if (optionDao.findOne(delid) != null) optionDao.delete(delid);
		DeliverOption opt = optionDao.findOne(id);
		// prepare a delivery summary
		DeliveryService.setDeliverSummery(opt, summaryDao);
		// set the delivery
		return DeliveryService.setDelivery(opt);
	}
}
