package springboot.model;

// this is will be passed to front end showing the track path
public class latlonGroup {
	private double[] start;
	private double[] current;
	private double[] finish;
	private String startAddr;
	private String finishAddr;
	public double[] getStart() {
		return start;
	}
	public void setStart(double[] start) {
		this.start = start;
	}
	public double[] getCurrent() {
		return current;
	}
	public void setCurrent(double[] current) {
		this.current = current;
	}
	public double[] getFinish() {
		return finish;
	}
	public void setFinish(double[] finish) {
		this.finish = finish;
	}
	public String getStartAddr() {
		return startAddr;
	}
	public void setStartAddr(String startAddr) {
		this.startAddr = startAddr;
	}
	public String getFinishAddr() {
		return finishAddr;
	}
	public void setFinishAddr(String finishAddr) {
		this.finishAddr = finishAddr;
	}
	
}
