package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import springboot.model.Sender;

import java.io.IOException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendTrackingId(String email, String trackingId) {
    	ResourcePropertySource resourcePropertySource = null;
		try {
			resourcePropertySource = new ResourcePropertySource("resoutces", "classpath:application.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    	String test = "Your tracking id is " + trackingId;
    	simpleMailMessage.setFrom(resourcePropertySource.getProperty("spring.mail.username").toString());
    	simpleMailMessage.setTo(email);
    	simpleMailMessage.setSubject("Tracking ID");
    	simpleMailMessage.setText(test);

    	javaMailSender.send(simpleMailMessage);
	}

}
