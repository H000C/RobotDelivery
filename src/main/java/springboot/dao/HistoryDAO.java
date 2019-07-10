package springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.History;

@Service
public class HistoryDAO {

    @Autowired
    HistoryRepository historyRepository;

    public History save(History history) {return historyRepository.save(history);}

    public void delete(History history) {historyRepository.delete(history);}


}
