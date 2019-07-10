package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.dao.UserDAO;
import springboot.model.User;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User findUser(String username)
    {
        for(User u:userDAO.findAll())
        {
            if (u.getUsername().equals(username))
            {
                return u;
            }
        }
        return null;
    }


    public void saveUser(String username, String password)
    {
        User user= new User();
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        user.setUsername(username);
//        user.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));
        user.setPassword(password);
        userDAO.save(user);
    }

    public boolean verifyLogin(String username, String password) {

        return userDAO.verifyUser(username, password);

    }



}
