package springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.model.DeliverSummary;

public interface SummaryRepository extends JpaRepository<DeliverSummary, String>{

}
