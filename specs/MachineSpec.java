package evtconvertor.specs;

public class MachineSpec extends Spec{
	
	private String logic;
	private String name;
	private String operator;
	private int startLine;
	private int endLine;
	private Spec parentSpec;
	private String parentSpecString;
	
	public MachineSpec(String logic, String name, String parentSpecString, String operator, int startLine, int endLine) {
		super(logic, name, parentSpecString, operator, startLine, endLine);		
		this.logic = logic.trim();
		this.name = name.trim();
		this.operator = operator.trim();
		this.startLine = startLine;
		this.endLine = endLine;
		this.parentSpecString = parentSpecString;
		
	}

}
