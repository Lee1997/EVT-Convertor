package evtconvertor.specs;

import java.util.ArrayList;

import evtconvertor.parcel.Invariant;
import evtconvertor.parcel.Parser;
import evtconvertor.parcel.Variable;

public class CarrierSpec extends Spec{
	

	private String logic;
	private String name;
	private String operator;
	private String parentSpecString;
	private int startLine;
	private int endLine;
	
	public CarrierSpec(String logic, String name, String parentSpecString, String operator, int startLine, int endLine) {
		super(logic, name, parentSpecString, operator, startLine, endLine);
		this.logic = logic.trim();
		this.name = name.trim();
		this.operator = operator.trim();
		this.startLine = startLine;
		this.endLine = endLine;
		this.parentSpecString = parentSpecString;

		
		searchOperations();
	}
	
	public void searchOperations() {
		ArrayList<String> evtCode = Parser.getEvtCode();
		for(int lineC = startLine + 1; lineC < endLine; lineC++) {
			String line = evtCode.get(lineC);
			if('.' == line.charAt(0)) { //It is a predicate/invariant/axiom
				invariants.add(new Invariant(line.substring(1).trim()));
				continue;
			}else {
				//Remove the word ops
				if("ops".equals(line.substring(0, 3))) {
					line = line.substring(3).trim();
				}
				if("op".equals(line.substring(0, 2))) {
					line = line.substring(2).trim();
				}
				String type = "default";
				type = line.split(":")[1].trim();
				
				//Remove type
				line = line.substring(0, line.indexOf(':')).trim();
				
				//Check for multiple variable type definitions
				if(line.indexOf(',') != -1) {
					for(String s : line.split(",")) {
						addVariable(new Variable(s.trim(), type));
					}
				}else {
					addVariable(new Variable(line, type));
				}
			}
		}
	}
	
	private void addVariable(Variable variable) {
		variables.add(variable);
	}

	public String getParentSpecString() {
		return parentSpecString;
	}

	public void setParentSpecString(String parentSpecString) {
		this.parentSpecString = parentSpecString;
	}

	public ArrayList<String> getOperations() {
		return operations;
	}

	public void setOperations(ArrayList<String> operations) {
		this.operations = operations;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}
	

	
	
}
