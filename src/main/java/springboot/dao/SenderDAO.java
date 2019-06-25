/*
 * SenderDAO (Data Access Object):
 *     to access the Create Read Update Delete functions
 * 
 * Created by: Haochen Liu
 */

package springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.model.Sender;

@Service
public class SenderDAO {
	
	@Autowired
	SenderRepository senderRepository;

	/*to save a sender*/
	public Sender save(Sender sender) {
		return senderRepository.save(sender);
	} 
	
	
	/* search all sender*/
	public List<Sender> findAll(){
		return senderRepository.findAll();
	}
	
	
	/*get a sender by id*/
	public Sender findOne(String orderid) {
		return senderRepository.findOne(orderid);
	}
	
	
	/*delete a sender*/
	public void delete(Sender sender) {
		senderRepository.delete(sender);
	}
}
