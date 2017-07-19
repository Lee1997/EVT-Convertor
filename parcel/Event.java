package evtconvertor.parcel;

import java.util.ArrayList;

public class Event {
	
	private String name;
	private ArrayList<EventComponent> components;
	
	public Event(String name) {
		this.name = name;
		components = new ArrayList<EventComponent>();
	}
	
}
