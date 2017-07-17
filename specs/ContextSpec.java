package evtconvertor.specs;

import java.util.ArrayList;

public class ContextSpec extends Spec{
	
	private String logic;
	private String name;
	private String operator;
	private String parentSpecString;
	private int startLine;
	private int endLine;
	private String set;
	private ArrayList<String> constants;
	private String axiom;
	
	public ContextSpec(String logic, String name, String parentSpecString, String operator, int startLine, int endLine) {
		super(logic, name, parentSpecString, operator, startLine, endLine);
		this.logic = logic.trim();
		this.name = name.trim();
		this.operator = operator.trim();
		this.startLine = startLine;
		this.endLine = endLine;
		this.parentSpecString = parentSpecString;
		constants = new ArrayList<String>();
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

	public String getParentSpecString() {
		return parentSpecString;
	}

	public void setParentSpecString(String parentSpecString) {
		this.parentSpecString = parentSpecString;
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

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}

	public ArrayList<String> getConstants() {
		return constants;
	}

	public void setConstants(ArrayList<String> constants) {
		this.constants = constants;
	}

	public String getAxiom() {
		return axiom;
	}

	public void setAxiom(String axiom) {
		this.axiom = axiom;
	}
	
	
	
}
