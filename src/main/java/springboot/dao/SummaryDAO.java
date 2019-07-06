package springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.model.DeliverOption;
import springboot.model.DeliverSummary;

@Service
public class SummaryDAO {
	
	@Autowired
	SummaryRepository summaryRepository;
	
	public DeliverSummary save(DeliverSummary summary) {
		return summaryRepository.save(summary);
	} 
	
	public List<DeliverSummary> findAll(){
		return summaryRepository.findAll();
	}
	
	public DeliverSummary findOne(String trackingid) {
		return summaryRepository.findOne(trackingid);
	}
	
	public void delete(String trackingid) {
		summaryRepository.delete(trackingid);
	}

}
