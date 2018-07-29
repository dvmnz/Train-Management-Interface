package si.um.opj.David_Moniz.logic.cargo;

public class Item implements Portable, java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1947234298554737605L;
	private String name;
	private int weight;
	
	public Item(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Item created: Name: " + name + " ; weight=" + weight + "]";
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String returnNameOfTheItem() {
		return name;
	}

	@Override
	public int returnWeightOfTheItem() {
		return weight;
	}
	
}
