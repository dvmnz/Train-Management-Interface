package si.um.opj.David_Moniz.logic.transport;


public abstract class  Train implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7719686525648123557L;
	private String name;
	private float averageSpeed;
	public abstract double getOcupancy();
	/**
	 * Default constructor 
	 */
	public Train() {
	}
	/**
	 * Parameterized constructor
	 * @param name
	 */
	public Train(String name) {
		this.name = name;
	}
	/**
	 * Parameterized constructor 2
	 * @param name
	 * @param averageSpeed
	 */
	public Train(String name, float averageSpeed) {
		super();
		this.name = name;
		this.averageSpeed = averageSpeed;
	}
	/**
	 * 
	 * @return
	 */
	public String getname() {
		return name;
	}
	/**
	 * 
	 * @param name
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return
	 */
	public float getAverageSpeed() {//must be km/h
		return averageSpeed;
	}
	/**
	 * 
	 * @param averageSpeed
	 */
	public void setAverageSpeed(float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}



	@Override
	public String toString() {
		return "Train's name is " + name + " and the average speed is " + averageSpeed;
	}
}
