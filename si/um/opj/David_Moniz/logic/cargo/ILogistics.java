package si.um.opj.David_Moniz.logic.cargo;

public interface ILogistics extends java.io.Serializable{

	public void loadContainer(Container container) throws FreightTrainFullException, MaximumWeightExceededException;
	public void unloadContainer(Container container);
}
