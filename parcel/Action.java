package evtconvertor.parcel;

public class Action extends EventComponent{
	
	private String label;
	private String eventOperation;
	
	public Action(String label, String eventOperation) {
		super(label, eventOperation);
		this.label = label;
		this.eventOperation = eventOperation;
	}

}
