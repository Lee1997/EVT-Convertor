package evtconvertor.parcel;

import java.util.ArrayList;

public class Parser {
	
	private ArrayList<String> evtCode;
	private String currentLogic;
	private ArrayList<Spec> specs;
	
	public void init() {
		boolean workingOnSpec = false;
		String type = "default";
		String name = "default";
		Spec parentSpec = null;
		String operator = null;
		int startLine = -1;
		int endLine = -1;
		
		for(int i = 0; i < evtCode.size(); i++) {
			String line = evtCode.get(i);
			if(line.length() > 5 && "logic".equals(line.substring(0, 5))) {
				//Manages switching between logic EVT and logic CASL
				currentLogic = line.substring(6);
			}
			if(line.length() >= 4 && "spec".equals(line.substring(0, 4)) && !workingOnSpec) {
					name = line.substring(5, line.length()-1);
					line = evtCode.get(++i);
					type = currentLogic;
					line = evtCode.get(++i);
					startLine = i;
					operator = line;
					workingOnSpec = true;
			}
			if(line.length() >= 3 && ("end".equals(line.substring(0, 3)) || i == evtCode.size() -1)){
				endLine = i;
				specs.add(new Spec(type, name, parentSpec, operator, startLine, endLine));
				workingOnSpec = false;
			}
			
		}
	}
	
	public Parser(ArrayList<String> evtCode) {
		this.evtCode = evtCode;
		this.specs = new ArrayList<Spec>();
	}

	public ArrayList<Spec> getSpecs() {
		return specs;
	}
	
}
