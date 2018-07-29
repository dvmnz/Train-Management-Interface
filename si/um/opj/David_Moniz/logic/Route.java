package si.um.opj.David_Moniz.logic;


import java.time.LocalDate;

import si.um.opj.David_Moniz.logic.transport.Train;

public class Route {

	private LocalDate startDate;
	private float routeDistance;//must be km
	private City startingCity;
	private City destinationCity;
	private Train train;
	/**
	 * Default constructor 
	 */
	public Route() {
		startDate = LocalDate.now();
	}
	/**
	 * Parameterized constructor(1):
	 * @param startingCity
	 * @param destinationCity
	 */
	public Route(City startingCity,City destinationCity) {
		this.startingCity = startingCity;
		this.destinationCity = destinationCity;
		startDate = LocalDate.now();
	}
	@Override
	public String toString() {
		return "Origin City:\n\t " + startingCity
				+ "\n" 
				+ "Destination City:\n\t -" + destinationCity
				+ "\n" 
				+ "Date of Start:\n\t -" + startDate
				+ "\n" 
				+ "Route Distance:\n\t - " + routeDistance
				+ "\n" 
				+ "Train:\n\t -" + train;
	}
	/**
	 * Calculates the date of Arrival if it takes more than one day or not
	 * @return
	 */
	public LocalDate calculateDateOfTheArrival() {
		float trainSpeed = train.getAverageSpeed();
		float calculateHours = (routeDistance / trainSpeed);
		int calculateDays = (int) calculateHours/24;
		return startDate.plusDays(calculateDays);
	}
	
	/**
	 * Get Start date
	 * @return
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	/**
	 * Set Start date
	 * @param startDate
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	/**
	 * Get Route Distance
	 * @return
	 */
	public float getRouteDistance() {
		return routeDistance;
	}
	/**
	 * Set Route Distance
	 * @param routeDistance
	 */
	public void setRouteDistance(float routeDistance) throws IllegalArgumentException {
		if(routeDistance<0) {
			throw new IllegalArgumentException("negative routeDistance");
		}
		this.routeDistance = routeDistance;
	}
	/**
	 * Get city of origin
	 * @return
	 */
	public City getStartingCity() {
		return startingCity;
	}
	/**
	 * Set City of origin
	 * @param startingCity
	 */
	public void setStartingCity(City startingCity) {
		this.startingCity = startingCity;
	}
	/**
	 * Get city of Destination 
	 * @return
	 */
	public City getDestinationCity() {
		return destinationCity;
	}
	/**
	 * Set Destination
	 * @param destinationCity
	 */
	public void setDestinationCity(City destinationCity) {
		this.destinationCity = destinationCity;
	}
	/**
	 * 
	 * @return
	 */
	public Train getTrain() {
		return train;
	}
	/**
	 * 
	 * @param train
	 */
	public void setTrain(Train train) {
		this.train = train;
	}

	
}
