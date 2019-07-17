package springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.User;

import java.util.List;

@Service
public class UserDAO{
    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean verifyUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password) != null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
