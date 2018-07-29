package si.um.opj.David_Moniz.logic.transport;

public class PassengerTrain extends Train{

	private int maxNumberOfSeats;
	private int maxNumberOfHandholds;
	private int numberOfSoldSeats;
	private int numberOfSoldHandholds;
	private PassengerTrainType p;
	
	public PassengerTrain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PassengerTrain(String name, float averageSpeed) {
		super(name, averageSpeed);
		// TODO Auto-generated constructor stub
	}
	public PassengerTrain(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public PassengerTrain(String name, float averageSpeed, int maxNumberOfSeats, int maxNumberOfHandholds, int numberOfSoldSeats,
			int numberOfSoldHandholds, PassengerTrainType ps) {
		super(name,averageSpeed);
		this.maxNumberOfSeats = maxNumberOfSeats;
		this.maxNumberOfHandholds = maxNumberOfHandholds;
		this.numberOfSoldSeats = numberOfSoldSeats;
		this.numberOfSoldHandholds = numberOfSoldHandholds;
		this.p = ps; 
	}
	
	public int getMaxNumberOfSeats() {
		return maxNumberOfSeats;
	}
	public void setMaxNumberOfSeats(int maxNumberOfSeats) {
		this.maxNumberOfSeats = maxNumberOfSeats;
	}
	public int getMaxNumberOfHandholds() {
		return maxNumberOfHandholds;
	}
	public void setMaxNumberOfHandholds(int maxNumberOfHandholds) {
		this.maxNumberOfHandholds = maxNumberOfHandholds;
	}
	public int getNumberOfSoldSeats() {
		return numberOfSoldSeats;
	}
	public void setNumberOfSoldSeats(int numberOfSoldSeats) {
		this.numberOfSoldSeats = numberOfSoldSeats;
	}
	public int getNumberOfSoldHandholds() {
		return numberOfSoldHandholds;
	}
	public void setNumberOfSoldHandholds(int numberOfSoldHandholds) {
		this.numberOfSoldHandholds = numberOfSoldHandholds;
	}
	
	@Override
	public String toString() {
		return super.toString() + "PassengerTrain [maxNumberOfSeats=" + maxNumberOfSeats + ", maxNumberOfHandholds=" + maxNumberOfHandholds
				+ ", numberOfSoldSeats=" + numberOfSoldSeats + ", numberOfSoldHandholds=" + numberOfSoldHandholds + "]";
	}
	
	/**
	 * 
	 * returns the percentage of sold handholds and seats
	 * 
	 */
	@Override
	public double getOcupancy() {
			int sold = this.numberOfSoldHandholds + this.numberOfSoldSeats;
			int max = this.maxNumberOfHandholds + this.maxNumberOfSeats;
			double value = (double)(sold)*100/(double)(max);
			
			return value;
	}
	
	
	
}
