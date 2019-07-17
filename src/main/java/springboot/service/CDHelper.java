package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.ProjectConstants;
import springboot.dao.OptionDAO;
import springboot.model.DeliverOption;
import springboot.model.latlonGroup;

@Service
public class CDHelper {
	
	@Autowired
	OptionDAO optionDao;
	
	public latlonGroup getPhaseRoute(String Phase, String trackingid) {
		DeliverOption option = optionDao.findOne(trackingid);
		latlonGroup llg = new latlonGroup();
		if (Phase.equals("leaving")) {
			llg.setStart(getStationCoord(option.getStartStation()));
			llg.setFinish(option.getPickupLatLon());
		}
		else if (Phase.equals("pickup")) {
			llg.setStart(option.getPickupLatLon());
			llg.setFinish(option.getDropoffLatLon());
		}
		else {
			llg.setStart(option.getDropoffLatLon());
			llg.setFinish(getStationCoord(option.getEndStation()));
		}
		return null;
	}
	
	private double[] getStationCoord(int id) {
		if (id == 1) {
			return ProjectConstants.station1_latlon;
		}
		else if (id == 2) {
			return ProjectConstants.station2_latlon;
		}
		else if (id == 3) {
			return ProjectConstants.station3_latlon;
		}
		return null;
	}
}
