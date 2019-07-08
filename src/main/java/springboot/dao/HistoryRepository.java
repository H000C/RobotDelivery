package springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.model.History;

public interface HistoryRepository extends JpaRepository<History, Integer> {

}
