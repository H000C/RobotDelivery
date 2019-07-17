package springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.model.DeliverOption;

@Service
public class OptionDAO {
	
	@Autowired
	OptionRepository optionrepository;
	
	public DeliverOption save(DeliverOption option) {
		return optionrepository.save(option);
	} 
	
	public List<DeliverOption> findAll(){
		return optionrepository.findAll();
	}
	
	public DeliverOption findOne(String trackingid) {
		return optionrepository.findOne(trackingid);
	}
	
	public void delete(String trackingid) {
		optionrepository.delete(trackingid);
	}
	

}
