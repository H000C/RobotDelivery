package springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import springboot.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findByUsername(@Param("username") String username);


}
