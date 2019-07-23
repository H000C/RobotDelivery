package springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {
	
    @GetMapping("/paypal-transaction-complete")
    public String setPackage(String orderid){
        return "done";
    }

}
