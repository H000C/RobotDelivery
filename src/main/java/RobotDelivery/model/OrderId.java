package RobotDelivery.model;

public class OrderId {
	private String orderid;
	
	public OrderId() {
		super();
	}
	
	public OrderId(String orderid) {
		super();
		this.orderid = orderid;
	}
	
	public OrderId(long orderid) {
		super();
		this.orderid = Long.toString(orderid);
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}
