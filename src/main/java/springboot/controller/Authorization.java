package springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import springboot.model.User;
import springboot.service.EmailService;
import springboot.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class Authorization {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        System.out.println("here is in singup url");
        System.out.println(user.getUsername() + user.getPassword());
        if(userService.findUser(user.getUsername())!=null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username already existed!\n");
        }

        userService.saveUser(user.getUsername(), user.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body("Register success!!\n");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpSession session, @RequestBody User user) {

        System.out.println("LOGIN");
        String username = user.getUsername();
        String password = user.getPassword();
        if (userService.findUser(username) == null) {
            return ResponseEntity.status(401).body("Username do not exist!!");
        }
        if (userService.verifyLogin(username, password)) {
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(600);
            return ResponseEntity.status(HttpStatus.OK).body("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is incorrect!!");
        }

    }

    @GetMapping("/test")
    public ResponseEntity<String> test()

    {
//        emailService.sendTrackingId("xlifocus@gmail.com", "0000000011111");
        return ResponseEntity.status(HttpStatus.OK).body("Hello World\n");

    }

}
