/*
 * Descriptions to be continued
 * run this as a java application to initiate Springboot
 * 
 * Created by: Haochen Liu
 */
package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RobotDeliveryApp {

	public static void main(String[] args) {
		SpringApplication.run(RobotDeliveryApp.class, args);
		
	}

}

