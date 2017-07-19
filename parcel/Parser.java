package evtconvertor.parcel;

import java.util.ArrayList;
import java.util.Arrays;

import evtconvertor.specs.CarrierSpec;
import evtconvertor.specs.ContextSpec;
import evtconvertor.specs.MachineSpec;
import evtconvertor.specs.Spec;

public class Parser {
	
	private static ArrayList<String> evtCode;
	private static String alert = "Converted"; // This will change if there is errors
	private String currentLogic;
	private ArrayList<Spec> genericSpecs; //This will store the specs before we know what type they are
	private ArrayList<Spec> specs; // This will store the final specs
	
	public void init() {
		//Initialize specs
		specs = new ArrayList<Spec>();
		
		//Gather info on all specs in file and store in the AL specs
		collectSpecs();
				
		//Finds the spec of type bool and add it to specs
		findBoolSpec();
		
		//Find the specs with no parents and set their type to be context and add them to specs
		findContextSpecs();
		
		//Finds the specs that have operations, which I have called Carrier Specs
		findCarrierSpecs();
		
		//Find the specs that will generate machine elements from their code, eg. Events, Action, guards, etc
		findMachineSpecs();
		
		//Each time a spec is found and specified, it is add to specs and removed from genericSpecs
		//So hopefully at this point genericSpecs will be fully depleted and specs will have the total number
		
		//Sets the bool and context files are completed.
		//Completed in the sense of they will not need any inheritance.
		//No files are created in this code, it's all internal.
		setBoolContext();
		
		//
		manageInheritance();
		
	}
	
	public void manageInheritance() {
		//Loop through each spec and get everything from their 
		
		for(int i = 0; i < specs.size(); i++) {
			Spec spec = specs.get(i);
			System.out.println(spec.getName() + ": " + spec.getClass().toString());
			if(spec.getClass().toString().contains("CarrierSpec")) {
				System.out.println("Variables of " + spec.getName());
				for(Variable v : spec.getVariables())
					System.out.println();
			}
		}
	}
	
	public void setBoolContext() {
		
		//Declare all the bool and context files as completed
		for(int i = 0; i < specs.size(); i++) {
			Spec spec = specs.get(i);
			if(spec.getParentSpecString() == null) {
				if(spec.getClass().getName().equals("evtconvertor.specs.CarrierSpec")) {
					//It is a carrier spec of type bool, 
					//which I defaulted to null as they can be made without checking other spec
					spec.setMade(true);
				}
				else if(spec.getClass().getName().equals("evtconvertor.specs.ContextSpec")) {
					spec.setMade(true);
				}
			}
		}	
	}
	
	private void findMachineSpecs() {
		for(int specC = 0; specC < genericSpecs.size(); specC++) {
			boolean hasParent = false; //This for if it has a parent spec other than BOOL
			Spec genSpec = genericSpecs.get(specC);
			String line = evtCode.get(genSpec.getStartLine() + 1);
			if(line.contains("event")) {
				MachineSpec machineSpec = new MachineSpec(
									genSpec.getLogic(), 
									genSpec.getName(),
									evtCode.get(genSpec.getStartLine() - 1), // Parent spec string
									genSpec.getOperator(), 
									genSpec.getStartLine(), 
									genSpec.getEndLine()
									);
				specs.add(machineSpec);
				genericSpecs.remove(specC);
				specC = -1;
			}
		}
	}
	
	private void findCarrierSpecs() {
		for(int specC = 0; specC < genericSpecs.size(); specC++) {
			Spec genSpec = genericSpecs.get(specC);
			String parent = evtCode.get(genSpec.getStartLine() - 1);
			if("op".equals(evtCode.get(genSpec.getStartLine() + 1).substring(0, 2))) { 
				//It is a carrier spec and you must loop until the end
				CarrierSpec carrierSpec = new CarrierSpec(
									genSpec.getLogic(), 
									genSpec.getName(),
									parent,
									genSpec.getOperator(), 
									genSpec.getStartLine(), 
									genSpec.getEndLine()
									);
				specs.add(carrierSpec);
				genericSpecs.remove(specC);
				specC = 0;
			}
		}
	}
	
	private void findContextSpecs() {
		for(int specC = 0; specC < genericSpecs.size(); specC++) {
			Spec genSpec = genericSpecs.get(specC);
			String parent= null;
			String line = evtCode.get(genSpec.getStartLine() - 1);
			if(line.contains("free type")) {
				ContextSpec contextSpec = new ContextSpec(
										genSpec.getLogic(), 
										genSpec.getName(),
										parent,
										genSpec.getOperator(), 
										genSpec.getStartLine(), 
										genSpec.getEndLine()
										);
				//TODO what symbol is this?
				String set = line.substring(10, line.indexOf("::="));
				line = line.substring(line.indexOf("::=") + 3);
				
				ArrayList<String> constants = new ArrayList<>(Arrays.asList(line.split("|")));
				
				contextSpec.setSet(set);
				contextSpec.setConstants(constants);
				
				
				specs.add(contextSpec);
				genericSpecs.remove(specC);
				specC = 0;
			}
		}
	}
	
	private boolean alreadyAdded(String name) {
		for(int i = 0; i < specs.size(); i++) {
			if(specs.get(i).getName().equals(name))
				return true;
		}
		
		return false;
	}
	
	private void findBoolSpec() {
		for(int specC = 0; specC < genericSpecs.size(); specC++) {
			Spec genSpec = genericSpecs.get(specC);
			String parent = null;
			if(evtCode.get(genSpec.getStartLine()-1).equalsIgnoreCase("BOOL")) {
				//If the spec is of type Bool
				CarrierSpec boolSpec = new CarrierSpec(
										genSpec.getLogic(), 
										genSpec.getName(),
										parent,
										genSpec.getOperator(), 
										genSpec.getStartLine(), 
										genSpec.getEndLine()
										);
				boolSpec.setType("carrier");
				specs.add(boolSpec);
				genericSpecs.remove(specC);
				specC = 0;
			}
		}
	}
	
	private boolean isMade(String name) {
		for(int i = 0; i < specs.size(); i++) {
			if(specs.get(i).getName().equals(name))
				return specs.get(i).isMade();
		}
		return false;
	}
	
	private void collectSpecs() {
		boolean workingOnSpec = false;
		String type = "default";
		String name = "default";
		String parentSpecString = "default";
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
					//If you find a spec definition, take the initial details of it
					name = line.substring(5, line.length()-1);
					line = evtCode.get(++i);
					parentSpecString = evtCode.get(i);
					line = evtCode.get(++i);
					startLine = i;
					operator = line;
					workingOnSpec = true;
			}
			if(line.length() >= 3 && ("end".equals(line.substring(0, 3)) || i == evtCode.size() -1)){
				//If you find the end of a spec, create the spec in memory 
				endLine = i;
				genericSpecs.add(new Spec(type, name, parentSpecString, operator, startLine, endLine));
				workingOnSpec = false;
			}
		}
	}
	
	public Parser(ArrayList<String> evtCode) {
		this.evtCode = evtCode;
		this.genericSpecs = new ArrayList<Spec>();
	}

	public ArrayList<Spec> getSpecs() {
		return specs;
	}

	public static ArrayList<String> getEvtCode() {
		return evtCode;
	}

	public void setEvtCode(ArrayList<String> evtCode) {
		this.evtCode = evtCode;
	}

	public String getCurrentLogic() {
		return currentLogic;
	}

	public void setCurrentLogic(String currentLogic) {
		this.currentLogic = currentLogic;
	}

	public ArrayList<Spec> getGenericSpecs() {
		return genericSpecs;
	}

	public void setGenericSpecs(ArrayList<Spec> genericSpecs) {
		this.genericSpecs = genericSpecs;
	}

	public void setSpecs(ArrayList<Spec> specs) {
		this.specs = specs;
	}
	
}
