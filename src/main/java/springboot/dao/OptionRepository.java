package springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.model.DeliverOption;


public interface OptionRepository extends JpaRepository<DeliverOption, String> {
	

}
