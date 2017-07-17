package evtconvertor.parcel;

public class Spec {
	
	private String type;
	private String name;
	private Spec parentSpec;
	private String operator;
	private int startLine;
	private int endLine;
	
	public Spec(String type, String name, Spec parentSpec, String operator, int startLine, int endLine){
		this.type = type;
		this.name = name;
		this.parentSpec = parentSpec;
		this.operator = operator;
		this.startLine = startLine;
		this.endLine = endLine;
	}
	
	@Override
	public String toString() {
		return "====================="
				+ "\nSpec name: " + name
				+ "\nOperator: " + operator
				+ "\nStart Line: " + startLine
				+ "\nEnd Line: " + endLine
				+ "\nLogic: " + type
				+ "\n=====================";
				
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Spec getParentSpec() {
		return parentSpec;
	}

	public void setParentSpec(Spec parentSpec) {
		this.parentSpec = parentSpec;
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
