package springboot.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.dao.OptionDAO;
import springboot.dao.RecipientDAO;
import springboot.dao.SenderDAO;
import springboot.dao.SummaryDAO;
import springboot.model.DeliverOption;
import springboot.model.DeliverSummary;
import springboot.model.latlonGroup;
import springboot.service.CalculateDistance;
import springboot.service.DeliveryService;
import springboot.service.OptionService;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class LocationController {

@Autowired
    SummaryDAO summaryDao;
@Autowired
    OptionDAO OptionDao;


    @RequestMapping("/trackOrder/currentLocation")
    @ResponseBody
    public latlonGroup getCurrentLocation(@Valid @RequestBody String trackingId){
        // make sure tracking id is not null
        if (trackingId == null) {
            System.out.println("Umm... No tracking id to track.");
            return null;
        }

        JSONObject object1 = new JSONObject(trackingId);
        String trackid = object1.getString("trackingid"); // trackid is the real traking id
        System.out.println(trackid);
        //get current time
        Date date = new Date();
        long time = date.getTime();// timestamp
        DeliverSummary summary = summaryDao.findOne(trackid);
        //get the timestamp you want in summary
        long dropoffTime = summary.getDropoffTime();
        long leavingTime = summary.getLeavingTime();
        long pickupTime = summary.getPickupTime();
        long returnTime = summary.getReturnTime();

        // afterthat call DetectPhase, return string phase
        String currentPhase = new CalculateDistance().DetectPhase(time, dropoffTime, leavingTime, pickupTime, returnTime);

        DeliverOption option = OptionDao.findOne(trackid);


        /// call DetectLocation to return double[] currentlocation
        latlonGroup currentLocation = new CalculateDistance().DetectLocation(time - summary.getLeavingTime(), option, currentPhase);
        /// add currentlocation to latlonGroup

        //?
        return currentLocation;


    }
}
