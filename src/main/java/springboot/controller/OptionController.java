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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import springboot.ProjectConstants;
import springboot.dao.OptionDAO;
import springboot.dao.PackageDAO;
import springboot.dao.RecipientDAO;
import springboot.dao.SenderDAO;
import springboot.dao.SummaryDAO;
import springboot.model.DeliverOption;
import springboot.model.Package;
import springboot.model.latlonGroup;
import springboot.service.CDHelper;
import springboot.service.DeliveryService;
import springboot.service.OptionService;
//import springboot.service.OptionService;

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
	
	@Autowired
	PackageDAO packageDao;

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
		String trackid = object.getString("trackingid"); // tracking id
		System.out.println(trackid);
		
		char[] c = trackid.toCharArray();
		String orderid = trackid.substring(0, trackid.length() - 1);
		Package packageObject = packageDao.findOne(orderid);

		if (packageObject == null) return null;
		System.out.println("package object is not null");
		
		if (c[c.length -1] == 'U') {// UAV set
			c[c.length -1] = 'R';
			packageObject.setServiceType("UAV");
		}
		else if (c[c.length -1] == 'R'){// Robot set
			c[c.length -1] = 'U';
			packageObject.setServiceType("Robot");
		}
		else return null;
		packageObject.setTrackingId(trackid);
		
		String delid = new String(c);
		if (optionDao.findOne(delid) != null) optionDao.delete(delid);
		DeliverOption opt = optionDao.findOne(trackid);
		// prepare a delivery summary
		DeliveryService.setDeliverSummery(opt, summaryDao);
		// set the delivery
		return DeliveryService.setDelivery(opt, optionDao);
	}
	
	@PostMapping ("/test")
	@ResponseBody
	public latlonGroup testing() {
		latlonGroup test = new latlonGroup();
		test.setStart(ProjectConstants.station1_latlon);
		test.setStartAddr(ProjectConstants.station1);
		test.setFinish(ProjectConstants.station3_latlon);
		test.setFinishAddr(ProjectConstants.station3);
		return CDHelper.getRobotTracking(test, 2);
		
	}
}
