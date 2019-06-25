/*
 * Recipient Model (Dynamic Data Structure):
 *     matches attributes in the recipient table
 *     automatically creates the recipient table in database
 *     
 * Created by: Haochen Liu
 */

package springboot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="recipient")
@EntityListeners(AuditingEntityListener.class)
public class Recipient {
	@Id
	private String  orderid;
	
	@NotBlank
	private String firstname; 
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String zipcode; 
	
	@NotBlank
	private String email; 
	
	@NotBlank
	private String phone;
	
	// this identifies the User
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	protected Date createdAt;
	
	public Recipient(String orderid, String firstname, String lastname, String address, String zipcode, String email,
			String phone, String createdBy, Date createdAt) {
		super();
		this.orderid = orderid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.zipcode = zipcode;
		this.email = email;
		this.phone = phone;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}
	
	public Recipient() {
		
	}
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
