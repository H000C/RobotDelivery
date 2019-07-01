/*
 * PointOnMap (data structure): 
 *     whether the point is valid
 *     its valid address acceptable by Bing Map
 *     its latitude, longitude
 *     
 * Created by: Haochen Liu
 * Modified by: Haochen Liu
 */
package springboot.model;

/* I haven't decided to use this as an entity
 * so I will leave this as it is for now
 */
public class PointOnMap {
	
	private boolean isValid;
	private String address;
	private double lat;
	private double lon;
	
	public PointOnMap(boolean isValid, String address, double lat, double lon) {
		super();
		this.isValid = isValid;
		this.address = address;
		this.lat = lat;
		this.lon = lon;
	}
	
	public PointOnMap() {
		super();
	}
	
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
}
