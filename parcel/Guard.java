package evtconvertor.parcel;

public class Guard extends EventComponent{
	
	private String label;
	private String eventOperation;
	
	public Guard(String label, String eventOperation) {
		super(label, eventOperation);
		this.label = label;
		this.eventOperation = eventOperation;
	}

}
