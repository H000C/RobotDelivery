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
    private String  orderid;


    private String serviceType;
    
    private String trackingId;


    @NotBlank
    private String size;

    @NotNull
    private double weight;

    private double cost;

    // this identifies the User
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date createdAt;


	public Package(String orderid, String serviceType, String trackingId, String size, double weight, double cost,
			String createdBy, Date createdAt) {
		super();
		this.orderid = orderid;
		this.serviceType = serviceType;
		this.trackingId = trackingId;
		this.size = size;
		this.weight = weight;
		this.cost = cost;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}


	public Package() {

    }


	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
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


