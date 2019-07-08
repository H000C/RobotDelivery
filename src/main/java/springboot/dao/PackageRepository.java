/*
 * SenderRespository interface for Create Read Update Delete
 * 
 * Created by: Haochen Liu
 */

package springboot.dao;
import springboot.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, String> {

}
