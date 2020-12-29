package springboot.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.model.User;
import springboot.service.EmailService;
import springboot.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

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
    @ResponseBody
    public User login(HttpSession session, @RequestBody User user) {

        System.out.println("LOGIN post");
        String username = user.getUsername();
        String password = user.getPassword();
        if (userService.findUser(username) == null) {
            ResponseEntity.status(401).body("Username do not exist!!");
        }
        if (userService.verifyLogin(username, password)) {
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(600);
            ResponseEntity.status(HttpStatus.OK).body("Login successful");
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is incorrect!!");
        }
        return user;
    }

    @PostMapping("/verifyLogin")
    public ResponseEntity verifyLogin(HttpSession session, @RequestBody String input) {

        System.out.println("LOGIN get" + input.toString());
        JSONObject jsonObject = new JSONObject(input);
        String username = null;
        if (jsonObject.get("username") != null) {
            username = jsonObject.get("username").toString();
        }
        Object localSession = session.getAttribute("username");
        if (session != null && username != null && localSession != null && localSession.equals(username)){
            System.out.println("in if" + username);
            session.setMaxInactiveInterval(600);
            return ResponseEntity.status(HttpStatus.OK).body("Already login.");
        } else {
            System.out.println("in else");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session is overdue!");
        }
    }

    @PostMapping("/logout")
    @ResponseBody
    public void logout(HttpSession session) {
        System.out.println("here is logout");
        if (session != null) {
            session.invalidate();
            ResponseEntity.status(HttpStatus.OK).body("Login successful");
        }

    }

    @GetMapping("/test")
    public ResponseEntity<String> test()

    {
//        emailService.sendTrackingId("xlifocus@gmail.com", "0000000011111");
        return ResponseEntity.status(HttpStatus.OK).body("Hello World\n");

    }

}
