package si.um.opj.David_Moniz.logic.cargo;

public class MaximumWeightExceededException extends Exception  implements java.io.Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5452960461788466460L;
	public MaximumWeightExceededException() { super(); }
	public MaximumWeightExceededException(String message) { super(message); }
}
