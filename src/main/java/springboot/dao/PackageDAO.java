/*
 * packageDAO (Data Access Object):
 *     to access the Create Read Update Delete functions
 * 
 * Created by:Shenyi Yu
 */

package springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.Package;
import java.util.List;

@Service
public class PackageDAO {
	
	@Autowired
	PackageRepository packageRepository;

	/*to save a package*/
	public Package save(Package pa) {
		return packageRepository.save(pa);
	} 
	
	
	/* search all package*/
	public List<Package> findAll(){
		return packageRepository.findAll();
	}
	
	
	/*get a package by id*/
	public Package findOne(String orderid) {
		return packageRepository.findOne(orderid);
	}
	
	
	/*delete a package*/
	public void delete(Package pa) {
		packageRepository.delete(pa);
	}




}
