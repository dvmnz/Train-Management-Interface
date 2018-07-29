package si.um.opj.David_Moniz.logic.cargo;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author David Moniz
 * This class Container serves to contain object items in one array, like a container.
 */
public class Container implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3697167257081241408L;
	private String label;
	private int maxWeight = 0;
	private int maxNumberOfItems = 0;
	private Vector<Item> arrayOfItems = new Vector<>();
	
	/**
	 * Default Constructor
	 */
	public Container() {}
	/**
	 * Parameterized constructor(1):
	 * @param label
	 * @param maxNumberOfItems
	 */
	public Container(String label, int maxNumberOfItems) {
		this.label = label;
		this.maxNumberOfItems = maxNumberOfItems;
	}
	/**
	 * Parameterized constructor(2):
	 * @param label
	 * @param maxWeight
	 * @param maxNumberOfItems
	 * @param arrayOfItems
	 */
	public Container(String label, int maxWeight, int maxNumberOfItems) {
		this(label,maxNumberOfItems);
		this.maxNumberOfItems = maxNumberOfItems;
		this.maxWeight = maxWeight;
	}

	/**
	 * To String funtion
	 */
	@Override
	public String toString() {
		return "Container [label=" + label + ", maxWeight=" + maxWeight + ", maxNumberOfItems=" + maxNumberOfItems
				+ ", arrayOfItems=" + arrayOfItems + "]";
	}

	/**
	 * Getter Label
	 * @return Label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Setter Label
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Getter Label
	 * @return Label
	 */
	public int getMaxWeight() {
		return maxWeight;
	}
	
	/**
	 * Setter of Max weight 
	 * @param maxWeight
	 */
	public void setMaxWeight(int maxWeight) {
		if(getCurrentWeightOfItems()<= maxWeight) {
			this.maxWeight = maxWeight;
		}
		else {
			System.out.println("Increase the value of the maximum weight. Current weight of items in this container: " + getCurrentWeightOfItems()+ " kg.");
		}
	}	
	
	/**
	 * Getter Label
	 * @return Label
	 */
	public int getMaxNumberOfItems() {
		return maxNumberOfItems;
	}
	
	/**
	 * 
	 * @param maxNumberOfItems
	 */
	public void setMaxNumberOfItems(int maxNumberOfItems) {
		if(arrayOfItems.size()<=maxNumberOfItems) {
			this.maxNumberOfItems = maxNumberOfItems;
		}
		else {
			System.out.println("Increase the value of the maximum number of items. Current number of items in this container: " + arrayOfItems.size()+ " items.");
		}
		
	}
	
	/**
	 * Getter Label
	 * @return Label
	 */
	public Vector<Item> getArrayOfItems() {
		return arrayOfItems;
	}
	
	public int getCurrentWeightOfItems() {
		int weight = 0;
		for(int i = 0; i < arrayOfItems.size(); i++) {
			weight = weight + arrayOfItems.get(i).returnWeightOfTheItem();
		}
		return weight;
	}
	
	/**
	 * Checks if there's a null element to add an item
	 * @param item
	 * @throws MaximumWeightExceededException 
	 * @throws ContainerFullException 
	 */
	public void addItem(Item item) throws MaximumWeightExceededException, ContainerFullException {
		int emptyContainer = maxNumberOfItems-arrayOfItems.size();	
		if(emptyContainer!=0) {
			if((getCurrentWeightOfItems()+item.returnWeightOfTheItem()) <= maxWeight) {
				arrayOfItems.add(item);
			}
			else {
				throw new MaximumWeightExceededException("The weight of the item exceeds the container's maximum weight");
			}
		}
		else {
			throw new ContainerFullException("The container exceeds the limit of items.");
		}
					
	}
	
	public void editItem(int index, Item item) throws MaximumWeightExceededException, ContainerFullException {
		System.out.println(item);
		int weight = getCurrentWeightOfItems() - arrayOfItems.get(index).returnWeightOfTheItem();
		int emptyContainer = maxNumberOfItems-arrayOfItems.size();
		
		if(emptyContainer!=0) {
			if((weight+item.returnWeightOfTheItem()) <= maxWeight) {
				arrayOfItems.set(index, item);
				System.out.println(item);
			}
			else {
				throw new MaximumWeightExceededException("The weight of the item exceeds the container's maximum weight");
			}
		}
		else {
			throw new ContainerFullException("The container exceeds the limit of items.");
		}			
	}
	
	/**
	 * Remove item from array. Checks if items exists to remove.
	 * @param item
	 */
	public void removeItem(Portable item){
		if(itemExists(item)) {
			for(int i=0; i < maxNumberOfItems; i++) {
				if(arrayOfItems.get(i) == item) {
					arrayOfItems.remove(i);
					break;
				}
			}
		}
	}
	
	/**
	 * Getter of Length of object items in array
	 * @return Length of items
	 */
	public  int  returnTheNumberOfItems(){
		return arrayOfItems.size();
	}
	
	/**
	 * Boolean function to see if item in array exists
	 * @param item
	 * @return true or false
	 */
	public boolean itemExists(Portable item){
		int n = arrayOfItems.size();
			for(int i=0; i < n; i++) {
				if(arrayOfItems.get(i).equals(item)) {
					return true;}
			}
			return false;
	}
	
}
