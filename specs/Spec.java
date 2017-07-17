package evtconvertor.specs;

public class Spec {
	
	private String logic;
	private String name;
	private String parentSpecString;
	private String operator;
	private int startLine;
	private int endLine;
	private String type;
	private boolean isMade;	
	
	public Spec(String logic, String name, String parentSpecString, String operator, int startLine, int endLine){
		this.logic = logic.trim();
		this.name = name.trim();
		this.operator = operator.trim();
		this.startLine = startLine;
		this.endLine = endLine;
		this.parentSpecString = parentSpecString;
		isMade = false;
	}
	
	@Override
	public String toString() {
		return "====================="
				+ "\nSpec name: " + name
				+ "\nSpec type: " + this.getClass()
				+ "\nOperator: " + operator
				+ "\nStart Line: " + startLine
				+ "\nEnd Line: " + endLine
				+ "\nLogic: " + logic
				+ "\n=====================";
				
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

	public String getParentSpecString() {
		return parentSpecString;
	}

	public void setParentSpecString(String parentSpec) {
		this.parentSpecString = parentSpec;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isMade() {
		return isMade;
	}

	public void setMade(boolean isMade) {
		this.isMade = isMade;
	}
	
}
