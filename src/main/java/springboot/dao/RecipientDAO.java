/*
 * RecipientDAO (Data Access Object):
 *     to access the Create Read Update Delete functions
 * 
 * Created by: Haochen Liu
 */

package springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.model.Recipient;

@Service
public class RecipientDAO {
	
	@Autowired
	RecipientRepository recipientRepository;
	
	/*to save a recipient*/
	public Recipient save(Recipient recipient) {
		return recipientRepository.save(recipient);
	} 
	
	
	/* search all recipient*/
	public List<Recipient> findAll(){
		return recipientRepository.findAll();
	}
	
	
	/*get a recipient by id*/
	public Recipient findOne(String orderid) {
		return recipientRepository.findOne(orderid);
	}
	
	
	/*delete a recipient*/
	public void delete(Recipient recipient) {
		recipientRepository.delete(recipient);
	}
}
