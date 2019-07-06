package springboot.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="deliverSummary")
@EntityListeners(AuditingEntityListener.class)
public class DeliverSummary {
	@Id
	private String Trackingid;
	
	@NotNull
	private long leavingTime;
	
	@NotNull
	private long pickupTime;
	
	@NotNull
	private long dropoffTime;
	
	@NotNull
	private long returnTime;

	public DeliverSummary(String trackingid, long leavingTime, long pickupTime, long dropoffTime, long returnTime) {
		super();
		Trackingid = trackingid;
		this.leavingTime = leavingTime;
		this.pickupTime = pickupTime;
		this.dropoffTime = dropoffTime;
		this.returnTime = returnTime;
	}
	
	public DeliverSummary() {
		super();
	}

	public String getTrackingid() {
		return Trackingid;
	}

	public void setTrackingid(String trackingid) {
		Trackingid = trackingid;
	}

	public long getLeavingTime() {
		return leavingTime;
	}

	public void setLeavingTime(long leavingTime) {
		this.leavingTime = leavingTime;
	}

	public long getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(long pickupTime) {
		this.pickupTime = pickupTime;
	}

	public long getDropoffTime() {
		return dropoffTime;
	}

	public void setDropoffTime(long dropoffTime) {
		this.dropoffTime = dropoffTime;
	}

	public long getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(long returnTime) {
		this.returnTime = returnTime;
	}
	
}
