/*
 * Package Model (Dynamic Data Structure):
 *     matches attributes in the package table
 *     automatically creates the package table in database
 *
 * Created by: Shenyi Yu
 */

package springboot.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="package")
@EntityListeners(AuditingEntityListener.class)
public class Package {
    //orderId serviceType size
    // shipDate arrivalDate what form?
    //weight cost double ?

    @Id
    private String  orderId;


    private String serviceType;
    private String trackingId;



    @NotBlank
    private String size;


    private String shipDate;


    private String arrivalDate;

    @NotNull
    private double weight;

    @NotNull
    private double cost;

    // this identifies the User
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date createdAt;

    public Package(String orderId, String serviceType, String size, String shipDate, String arrivalDate, double weight,
                   double cost, String createdBy, Date createdAt) {
        super();
        this.orderId = orderId;
        this.serviceType = serviceType;
        this.size = size;
        this.shipDate = shipDate;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
        this.cost = cost;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.trackingId = trackingId;
    }

    public Package() {

    }


    public String getorderId() {
        return orderId;
    }

    public void setorderId(String orderId) {
        this.orderId = orderId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}


