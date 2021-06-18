
public class Macro {
	
	private String body;
	protected String[] parameters;
	
	public Macro(String[] parameters) {
		body = "";
		this.parameters = parameters;
	}
	
	protected void newBodyLine(String line) {
		body = body + line;
	}
	
}
