package si.um.opj.David_Moniz.logic.transport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import si.um.opj.David_Moniz.logic.cargo.Container;
import si.um.opj.David_Moniz.logic.cargo.FreightTrainFullException;
import si.um.opj.David_Moniz.logic.cargo.ILogistics;
import si.um.opj.David_Moniz.logic.cargo.MaximumWeightExceededException;

public class FreightTrain extends Train implements ILogistics, java.io.Serializable{

	
	
	
	private static final long serialVersionUID = -4779705191249014423L;
	private int maxWeightOfCargo;
	private int maxNumberOfContainers;
	private Vector<Container> arrayOfContainers = new Vector<>();
	
	public FreightTrain() {
		super();
	}
	public FreightTrain(String name, int averageSpeed) {
		super(name, averageSpeed);
	}
	public FreightTrain(String name) {
		super(name);
	}
	public FreightTrain(String name, int averageSpeed,int maxWeightOfCargo, int maxNumberOfContainers) {
		super(name,averageSpeed);
		this.maxWeightOfCargo = maxWeightOfCargo;
		this.maxNumberOfContainers = maxNumberOfContainers;
	}
	
	public int getMaxWeightOfCargo() {
		return maxWeightOfCargo;
	}
	public void setMaxWeightOfCargo(int maxWeightOfCargo) {
		this.maxWeightOfCargo = maxWeightOfCargo;
	}
	public int getMaxNumberOfContainers() {
		return maxNumberOfContainers;
	}
	public void setMaxNumberOfContainers(int maxNumberOfContainers) {
		if(maxNumberOfContainers > arrayOfContainers.size()) {
			this.maxNumberOfContainers = maxNumberOfContainers;
		}
		else {
			System.out.println("You have containers on the Freight Train. Before you reduce the maximum number of containers, remove the containers.");
		}
	}
	public Container getContainer(int num) {
		return arrayOfContainers.get(num);
	}
	
	public Vector<Container> getArrayOfContainers() {
		return arrayOfContainers;
	}
	
	public int getRemaningWeightOfCargo() {
		int weight = 0;
		for(int i = 0; i < arrayOfContainers.size(); i++) {
				weight = weight + arrayOfContainers.get(i).getMaxWeight();
			}
		return maxWeightOfCargo-weight;
	}
	
	public int getRemaningWeightToEditCargo(Container container) {
		int weight = 0;
		for(int i = 0; i < arrayOfContainers.size(); i++) {
				weight = weight + arrayOfContainers.get(i).getMaxWeight();
			}
		return maxWeightOfCargo-weight+container.getMaxWeight();
	}
	
	@Override
	public String toString() {
		return super.toString() + "FreightTrain [maxWeightOfCargo=" + maxWeightOfCargo + ", maxNumberOfContainers=" + maxNumberOfContainers
				+  "]";
	}
	
	/**
	 * 
	 * returns the percentage of space occupied by loaded containers
	 *  
	 */
	@Override
	public double getOcupancy() {
		int numItems = 0;
		int max = 0;
		
		for(int i = 0; i < arrayOfContainers.size(); i++) {
				numItems = numItems + arrayOfContainers.get(i).returnTheNumberOfItems();
				max = max + arrayOfContainers.get(i).getMaxNumberOfItems();
			}
		
		return numItems*100/max;
	}
	
	
	@Override
	public void loadContainer(Container container) throws FreightTrainFullException, MaximumWeightExceededException {
		int sizeContainers = arrayOfContainers.size();
		
		int weight = 0;
		int emptyContainer = maxNumberOfContainers-sizeContainers;
		
		for(int i = 0; i < sizeContainers; i++) {
				weight = weight + arrayOfContainers.get(i).getMaxWeight();
			}		
		
		if(emptyContainer!=0) {
			if((weight+container.getMaxWeight()) <= maxWeightOfCargo) {
				arrayOfContainers.add(container);
			}
			else {
				throw new MaximumWeightExceededException("Reduce the weight of the container "
						+ "in order to load it to this freight train. ");
			}
		}
		else {
			throw new FreightTrainFullException("All containers of the Freight Train are full!");
		}
	}
	
	
	@Override
	public void unloadContainer(Container container) {
		for(int i = 0; i < maxNumberOfContainers; i++) {
			if(arrayOfContainers.get(i).equals(container)) {
				arrayOfContainers.remove(i);
				break;
			}
		}
	}
	
	
	
	
	
	
	
}
