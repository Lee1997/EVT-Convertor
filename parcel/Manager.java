package evtconvertor.parcel;

import java.util.ArrayList;

import evtconvertor.specs.Spec;

public class Manager {
	
	private ArrayList<Spec> specs;
	private ArrayList<String> evtCode;
	
	public Manager(ArrayList<Spec> specs, ArrayList<String> evtCode) {
		this.specs = specs;
		this.evtCode = evtCode;
	}
	
	private void init() {
		ArrayList<Spec> contexts = new ArrayList<Spec>();
		for(int specC = 0; specC < specs.size(); specC++) {
			Spec spec = specs.get(specC);
				contexts.add(spec);
			
		}
	}
	
}
