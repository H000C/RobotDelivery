package springboot.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="deliverOption")
@EntityListeners(AuditingEntityListener.class)
public class DeliverOption {

	@Id
	private String Trackingid;

	@NotBlank
	private String DeliveryOption;

	@NotNull
	private int startStation;

	@NotBlank
	private String pickupLocation;

	private double[] pickupLatLon;

	@NotBlank
	private String dropoffLoaction;

	private double[] dropoffLatLon;

	@NotNull
	private int endStation;

	@NotNull
	private double initialDistance;

	@NotNull
	private double deliveryDistance;

	@NotNull
	private double returnDistance;

	private String arrivingTime;

	public DeliverOption(String trackingid, String deliveryOption, int startStation, String pickupLocation,
						 double[] pickupLatLon, String dropoffLoaction, double[] dropoffLatLon, int endStation,
						 double initialDistance, double deliveryDistance, double returnDistance, String arrivingTime) {
		super();
		Trackingid = trackingid;
		DeliveryOption = deliveryOption;
		this.startStation = startStation;
		this.pickupLocation = pickupLocation;
		this.pickupLatLon = pickupLatLon;
		this.dropoffLoaction = dropoffLoaction;
		this.dropoffLatLon = dropoffLatLon;
		this.endStation = endStation;
		this.initialDistance = initialDistance;
		this.deliveryDistance = deliveryDistance;
		this.returnDistance = returnDistance;
		this.arrivingTime = arrivingTime;
	}

	public DeliverOption() {
		super();
	}

	public String getTrackingid() {
		return Trackingid;
	}

	public void setTrackingid(String trackingid) {
		Trackingid = trackingid;
	}

	public String getDeliveryOption() {
		return DeliveryOption;
	}

	public void setDeliveryOption(String deliveryOption) {
		DeliveryOption = deliveryOption;
	}

	public int getStartStation() {
		return startStation;
	}

	public void setStartStation(int startStation) {
		this.startStation = startStation;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDropoffLoaction() {
		return dropoffLoaction;
	}

	public void setDropoffLoaction(String dropoffLoaction) {
		this.dropoffLoaction = dropoffLoaction;
	}

	public int getEndStation() {
		return endStation;
	}

	public void setEndStation(int endStation) {
		this.endStation = endStation;
	}

	public double getInitialDistance() {
		return initialDistance;
	}

	public void setInitialDistance(double initialDistance) {
		this.initialDistance = initialDistance;
	}

	public double getDeliveryDistance() {
		return deliveryDistance;
	}

	public void setDeliveryDistance(double deliveryDistance) {
		this.deliveryDistance = deliveryDistance;
	}

	public double getReturnDistance() {
		return returnDistance;
	}

	public void setReturnDistance(double returnDistance) {
		this.returnDistance = returnDistance;
	}

	public String getArrivingTime() {
		return arrivingTime;
	}

	public void setArrivingTime(String arrivingTime) {
		this.arrivingTime = arrivingTime;
	}

	public double[] getPickupLatLon() {
		return pickupLatLon;
	}

	public void setPickupLatLon(double[] pickupLatLon) {
		this.pickupLatLon = pickupLatLon;
	}

	public double[] getDropoffLatLon() {
		return dropoffLatLon;
	}

	public void setDropoffupLatLon(double[] dropoffLatLon) {
		this.dropoffLatLon = dropoffLatLon;
	}

}
