/**
 * more of a dummy check before connect with front end
 * Creator: Shenyi Yu
 */


package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.dao.PackageDAO;
import springboot.service.PackageOrderService;
import springboot.model.Package;
import javax.validation.Valid;

@Controller
public class PackageController {
    @Autowired
    PackageDAO packageDAO;


    /*
     * saves an instance of package into package table
     * returns the saved instance
     */

    @PostMapping("/setOrder/setPackage")
    @ResponseBody
    public Package setPackage(@Valid @RequestBody Package pa){
        if (pa.getorderId() != null) return packageDAO.save(pa);
        PackageOrderService order = new PackageOrderService();
        pa.setorderId(order.generateOrder(pa));
        return packageDAO.save(pa);
    }



//    @RequestMapping("/trackOrder")
//    public String mapWebControl() {
//        return "TrackOrder";
//    }
}







