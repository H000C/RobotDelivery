package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.dao.HistoryDAO;
import springboot.model.History;

@Service
public class HistoryService {

    @Autowired
    HistoryDAO historyDAO;

    public History setHistory(String userId, String orderId, int status) {
        History history = new History(userId, orderId, status);
        return historyDAO.save(history);
    }
}
