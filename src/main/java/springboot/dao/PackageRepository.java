/*
 * Package Repository interface for Create Read Update Delete
 * 
 * Created by: Shenyi Yu
 */

package springboot.dao;
import springboot.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, String> {

}
